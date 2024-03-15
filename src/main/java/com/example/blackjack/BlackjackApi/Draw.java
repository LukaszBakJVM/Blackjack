package com.example.blackjack.BlackjackApi;

import java.util.List;

public record Draw(String remaining, List<Cards> cards) {
}
