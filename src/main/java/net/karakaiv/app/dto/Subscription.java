package net.karakaiv.app.dto;

import org.springframework.http.codec.ServerSentEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import reactor.core.publisher.FluxSink;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Subscription {
    
    private String token;

    private FluxSink<ServerSentEvent> fluxSink;    

}