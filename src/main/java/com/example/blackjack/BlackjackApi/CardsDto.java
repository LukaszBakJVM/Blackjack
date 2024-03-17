package com.example.blackjack.BlackjackApi;

import java.util.List;

public record CardsDto(String id,String remaining, List<String> cards,List<String>values, int sum) {
}
