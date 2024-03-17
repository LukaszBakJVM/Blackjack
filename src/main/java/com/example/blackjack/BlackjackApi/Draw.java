package com.example.blackjack.BlackjackApi;

import java.util.List;

public record Draw(String deck_id,String remaining, List<Cards> cards) {
}
