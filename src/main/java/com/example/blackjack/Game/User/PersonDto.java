package com.example.blackjack.Game.User;

import java.util.List;

public record PersonDto(String id, String remaining, List<String> cards, List<String>values, int sum) {
}
