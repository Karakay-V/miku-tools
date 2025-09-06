package net.karakaiv.app.network.tool.ping;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.karakaiv.app.model.Address;
import net.karakaiv.app.network.tool.Command;
import net.karakaiv.app.network.tool.ping.model.Result;

@Setter
@Getter
@AllArgsConstructor
@Slf4j
public class Ping extends Command implements Runnable {

    private final Thread currThread = Thread.currentThread();

    private final ArrayList<Result> results;

    private Address address;

    private Integer bytes = 32;

    private Integer count = 300;

    private static final Integer TIMEOUT = 5000; 

    private static final Long PAUSE = 1000L; 

    // FIXME: add ttl feature
    // private Integer ttl = 117;

    public Ping(ArrayList<Result> results, Address address) {
        this.results = results;
        this.address = address;
    }

    @Override
    public void run() {
        Integer done = 0;
        do {
            try {
                long start = System.nanoTime();

                InetAddress inet = InetAddress.getByName(address.toString());
                boolean reachable = inet.isReachable(TIMEOUT);

                long end = System.nanoTime();
                long rtt = (end - start) / 1_000_000;

                if (reachable) {
                    results.add(new Result(address, bytes, rtt));
                } else {
                    results.add(new Result(address, bytes, null));
                }

                done++;
                Thread.sleep(PAUSE);
            } catch (IOException | InterruptedException e) {
                log.error("Ping error: {}, address: {}, bytes: {}", e.getMessage(), address, bytes);
                log.error("Cause: {}", e.getCause());
            }
        } while (done < this.count);
    }
}
