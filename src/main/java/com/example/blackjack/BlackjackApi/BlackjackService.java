package com.example.blackjack.BlackjackApi;


import com.example.blackjack.Game.Game.CardsEnum;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class BlackjackService {
    private final WebClient webClient;

    private final String base = "https://deckofcardsapi.com";


    public BlackjackService(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl(base).build();

    }

    Mono<String> findId(int id) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/api/deck/new/shuffle/").queryParam("deck_count", id).build()).retrieve().bodyToMono(Blackjack.class).map(Blackjack::deck_id);
    }

    private Mono<Draw> drawCards(String deck_id, int count) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/api/deck/" + deck_id + "/draw/").queryParam("count", count).build()).retrieve().bodyToMono(Draw.class);
    }

    private int cardValue(List<String> cardValueFromApi) {
        return cardValueFromApi.stream().mapToInt(s -> Arrays.stream(CardsEnum.values()).filter(v -> v.getCARDS().equals(s)).findFirst().map(CardsEnum::getVALUES).orElse(0)).sum();
    }

    Mono<CardsDto> cardsImageEndSum(String deck_id, int count) {
        return drawCards(deck_id, count).map(draw -> new CardsDto(draw.deck_id(), draw.remaining(), draw.cards().stream().map(Cards::image).toList(), draw.cards().stream().map(Cards::value).toList(), cardValue(draw.cards().stream().map(Cards::value).toList())));


    }


}

