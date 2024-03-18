package com.example.blackjack.Game.Game;

import com.example.blackjack.Game.User.PersonDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @GetMapping("/show-cards/{id}")
    public String showCards(@PathVariable String id, @RequestParam(defaultValue = "0") int count, Model model) {

        PersonDto personDto = gameService.personDto(id, count);


        model.addAttribute("person", personDto);
        return "cardsView";
    }

    @GetMapping("/")
    String start() {
        return "gameView";
    }
}
