package com.example.blackjack.BlackjackApi;

import java.util.List;

public record CardsDto(String remaining, List<String> cards,List<String>values, int sum) {
}
