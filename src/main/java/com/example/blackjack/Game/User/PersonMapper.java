package com.example.blackjack.Game.User;

import com.example.blackjack.BlackjackApi.CardsDto;
import org.springframework.stereotype.Service;

@Service
public class PersonMapper {
    private int sum = 21;

    public PersonDto map(CardsDto cardsDto) {


        return new PersonDto(cardsDto.id(), cardsDto.remaining(), cardsDto.cards(), cardsDto.values(), points(cardsDto.sum()));
    }

    private int points(int startPoints) {

        sum -= startPoints;
        if (sum < 0) {
            sum = 21;


        }
        return sum;
    }
}
