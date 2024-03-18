package com.example.blackjack.Game.Game;

import com.example.blackjack.BlackjackApi.CardsDto;
import com.example.blackjack.Game.User.PersonDto;
import com.example.blackjack.Game.User.PersonMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GameService {
    private final RestTemplate restTemplate;
    private final PersonMapper personMapper;
    @Value("${cardsUrl}")
    private  String apiUrl ;
    private int sum = 21;

    public GameService(RestTemplate restTemplate, PersonMapper personMapper) {
        this.restTemplate = restTemplate;
        this.personMapper = personMapper;
    }
    PersonDto personDto(String id,int count) {
        String apiEndpoint = apiUrl + id + "?count=" + count;

        CardsDto forObject = restTemplate.getForObject(apiEndpoint, CardsDto.class);
      return personMapper.map(forObject);


    }
}
