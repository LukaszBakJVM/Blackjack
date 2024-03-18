package com.example.blackjack.Game.User;

import com.example.blackjack.BlackjackApi.CardsDto;
import org.springframework.stereotype.Service;

@Service
public class PersonMapper {
    PersonDto map(CardsDto cardsDto){
        return new PersonDto( cardsDto.sum());
    }
}
