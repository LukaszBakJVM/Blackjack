package com.example.blackjack.Game.Game;

import com.example.blackjack.BlackjackApi.CardsDto;
import com.example.blackjack.Game.User.Exception.GameOverException;
import com.example.blackjack.Game.User.PersonDto;
import com.example.blackjack.Game.User.PersonMapper;
import com.example.blackjack.Game.User.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GameService {

    private final WebClient webClient;
    private final PersonMapper personMapper;
    private final PersonRepository personRepository;



    public GameService(WebClient.Builder webClient, PersonMapper personMapper, PersonRepository personRepository) {

        this.webClient = webClient.baseUrl("http://localhost:8080/blackjack/").build();
        this.personMapper = personMapper;
        this.personRepository = personRepository;
    }

    Mono<PersonDto> personDto(String id, int count) {

        return webClient.get().uri(uriBuilder -> uriBuilder.path(id).queryParam("count", count).build()).retrieve().bodyToMono(CardsDto.class).handle((cardsDto, sink) -> {
            PersonDto map = personMapper.map(cardsDto);
            if (map.sum() < 0) {
                sink.error(new GameOverException(id));
            } else {
                sink.next(map);
            }
        });

    }
    int resetPoints(){
       return personMapper.points(21);

    }


}
