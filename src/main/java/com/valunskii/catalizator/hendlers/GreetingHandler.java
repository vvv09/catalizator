package com.valunskii.catalizator.hendlers;

import com.valunskii.catalizator.domain.Message;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class GreetingHandler {
    public Mono<ServerResponse> hello(ServerRequest request) {
        Long start = request.queryParam("start")
                .map(Long::valueOf)
                .orElse(0L);
        Long count = request.queryParam("count")
                .map(Long::valueOf)
                .orElse(3L);
        Flux<Message> data = Flux
                .just(
                        "Hello, reactive!",
                        "Hello, reactive2!",
                        "Hello, reactive3!",
                        "Hello, reactive4!",
                        "Hello, reactive5!"
                )
                .skip(start)//с какого элеметнта стартуем
                .take(count)//сколько элеметнтов хотим получить
                .map(Message::new);
    return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(data, Message.class);
    }

    public Mono<ServerResponse> index(ServerRequest request) {
        String user = request.queryParam("user").orElse("Nobody");
        return ServerResponse
                .ok()
                .render("index", Map.of("user", user));

    }

}
