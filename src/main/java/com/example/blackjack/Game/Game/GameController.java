package com.example.blackjack.Game.Game;

import com.example.blackjack.BlackjackApi.CardsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class GameController {
    private final RestTemplate restTemplate;
    @Value("${cardsUrl}")
    private String apiUrl;

    public GameController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/show-cards/{id}")
    public String showCards(@PathVariable String id, @RequestParam(defaultValue = "0") int count, Model model) {
        String apiEndpoint = apiUrl + id + "?count=" + count;

        CardsDto cardsDto = restTemplate.getForObject(apiEndpoint, CardsDto.class);
        model.addAttribute("cardsDto", cardsDto);
        return "cardsView";
    }

    @GetMapping("/")
    String start() {
        return "gameView";
    }
}
