package com.example.blackjack.Game.Game;

import com.example.blackjack.BlackjackApi.CardsDto;
import com.example.blackjack.Game.User.Exception.GameOverException;
import com.example.blackjack.Game.User.PersonDto;
import com.example.blackjack.Game.User.PersonMapper;
import com.example.blackjack.Game.User.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GameService {
    private final String url = "http://localhost:8080/blackjack/{id}?count={count}";


    private final PersonMapper personMapper;
    private final PersonRepository personRepository;
    private final RestTemplate restTemplate;


    public GameService(PersonMapper personMapper, PersonRepository personRepository, RestTemplate restTemplate) {


        this.personMapper = personMapper;
        this.personRepository = personRepository;
        this.restTemplate = restTemplate;
    }

    PersonDto personDto(String id, int count) {

        CardsDto forObject = restTemplate.getForObject(url, CardsDto.class, id, count);
        PersonDto map = personMapper.map(forObject);


        if (map.sum() < 0) {
            throw new GameOverException(id);
        } else {
            return map;
        }


    }

    int resetPoints() {
        return personMapper.points(21);

    }


}
