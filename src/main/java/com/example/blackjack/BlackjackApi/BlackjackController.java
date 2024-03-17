package com.example.blackjack.BlackjackApi;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/blackjack")
public class BlackjackController {

    private final BlackjackService blackjackService;


    public BlackjackController(BlackjackService blackjackService) {
        this.blackjackService = blackjackService;
    }

    @GetMapping("/id")
    Mono<ResponseEntity<?>> id(@RequestParam int id) {
        return blackjackService.findId(id).map(deckId -> {
            String url = "http://localhost:8080/show-cards/" + deckId;
            return ResponseEntity.status(302).header("Location", url).build();
        });
    }


    @GetMapping("/{deck_id}")
    Mono<CardsDto> value(@PathVariable String deck_id, @RequestParam(defaultValue = "0") int count) {
        return blackjackService.cardsImageEndSum(deck_id, count);
    }

}
