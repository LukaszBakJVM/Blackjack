package com.example.blackjack.BlackjackApi;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
public class BlackjackService {
    private final WebClient webClient;


    public BlackjackService(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }
  final   Mono<String>findId(int id){
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/api/deck/new/shuffle/")
                .queryParam("deck_count",id).build()).retrieve()
                .bodyToMono(Blackjack.class).map(Blackjack::deck_id);
    }
    Mono<Draw>drawCards(String deck_id,int count){
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/api/deck/"+deck_id+"/draw/")
                .queryParam("count",count).build()).retrieve()
                .bodyToMono(Draw.class);
    }


}
