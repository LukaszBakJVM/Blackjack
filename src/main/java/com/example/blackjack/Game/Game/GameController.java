package com.example.blackjack.Game.Game;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @GetMapping("/show-cards/{id}")
    public Mono<String> showCards(@PathVariable String id, @RequestParam(defaultValue = "0") int count, Model model) {

        return gameService.personDto(id, count).doOnNext(personDto -> model.addAttribute("person", personDto)).thenReturn("cardsView");


    }
    @GetMapping("/show-cards/reset21Points")
    int resetPoints(){
        return gameService.resetPoints();
    }

    @GetMapping("/")
    String start() {
        return "gameView";
    }
}
