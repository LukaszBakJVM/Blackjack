package com.example.blackjack.Game.Game;

import com.example.blackjack.BlackjackApi.CardsDto;
import com.example.blackjack.Game.User.PersonDto;
import com.example.blackjack.Game.User.PersonMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GameService {

    private final WebClient webClient;
    private final PersonMapper personMapper;


    public GameService(WebClient.Builder webClient, PersonMapper personMapper) {

        this.webClient = webClient.baseUrl("http://localhost:8080/blackjack/").build();
        this.personMapper = personMapper;
    }

    Mono<PersonDto> personDto(String id, int count) {

        return webClient.get().uri(uriBuilder -> uriBuilder.path(id).queryParam("count", count).build()).retrieve().bodyToMono(CardsDto.class).map(personMapper::map);


    }
}
