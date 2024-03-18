package com.example.blackjack.Game.User;

import com.example.blackjack.BlackjackApi.CardsDto;
import org.springframework.stereotype.Service;

@Service
public class PersonMapper {
    private int sum = 21;

    public PersonDto map(CardsDto cardsDto) {
        sum -= cardsDto.sum();
        if (sum < 0) {
            sum = 21;
        }

        return new PersonDto(cardsDto.id(), cardsDto.remaining(), cardsDto.cards(), cardsDto.values(), sum);
    }
}
