package net.karakaiv.app.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import net.karakaiv.app.dto.Subscription;
import net.karakaiv.app.dto.TokenData;
import net.karakaiv.app.model.StorageData;
import net.karakaiv.app.network.tool.ping.Ping;
import net.karakaiv.app.network.tool.ping.model.Result;
import net.karakaiv.app.storage.LocalStorage;
import net.karakaiv.app.token.TokenService;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
@RequestMapping(path = "/stream")
public class StreamController {

    @Autowired
    TokenService tokenService;

    Map<UUID, Subscription> subscriptions = new ConcurrentHashMap<>();

    private static final Long PAUSE = 1000L;

    @GetMapping(path = "/open/{token}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent> openSeeStream(@PathVariable("token") String token) {
        TokenData tokenData = tokenService.get(token);
        if (!tokenData.isExists())
                return Flux.error(new IllegalArgumentException("Invalid or expired token"));
        
        return Flux.create(fluxSink -> {
            log.info("Open stream: " + tokenData.toString());

            UUID uuid = UUID.randomUUID();

            fluxSink.onCancel(() -> {
                subscriptions.remove(uuid);
                tokenService.remove(token);
                log.info("Closed stream: " + tokenData.toString());
            });

            Subscription subscription = new Subscription(token, fluxSink);
            subscriptions.put(uuid, subscription);

            StorageData data = LocalStorage.getIfPresent(token);
            ArrayList<Result> results = new ArrayList<>();

            // Запускаємо Ping у фоні
            Thread thread = new Thread(() -> {
                try {
                    Ping ping = new Ping(results, data.getAddress());
                    ping.setCount(data.getPacketsCount());
                    ping.run(); // виконується у окремому потоці
                } catch (Exception e) {
                    fluxSink.error(e);
                }
            });
            thread.start();

            // Стрімимо останній результат
            new Thread(() -> {
                fluxSink.next(ServerSentEvent.builder("Pinging " + data.getAddress() + " with 32 bytes of data:").build());

                int lastSize = 0;
                while (!fluxSink.isCancelled()) {
                    try {
                        Thread.sleep(PAUSE);
                        if (results.size() > lastSize) {
                            Result r = results.get(results.size() - 1);
                            fluxSink.next(ServerSentEvent.builder(r.toString()).build());
                            lastSize = results.size();
                        }
                    } catch (InterruptedException e) {
                        log.error("Sse controller error: {}, address: {}", e.getMessage(), data.getAddress());
                        fluxSink.error(e);
                    }
                }
            }).start();        
        });
    }
}
