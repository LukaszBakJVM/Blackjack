package com.example.blackjack.Game.Game;

import com.example.blackjack.BlackjackApi.CardsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class GameController {
    @Value("${cardsUrl}")
    private  String apiUrl;
    private final RestTemplate restTemplate;

    public GameController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @GetMapping("/show-cards")
    public String showCards(@RequestParam String deck_id, Model model) {
        String apiEndpoint = apiUrl  + deck_id+"?count=12";
        System.out.println(apiEndpoint);
        CardsDto cardsDto = restTemplate.getForObject(apiEndpoint, CardsDto.class);
        model.addAttribute("cardsDto", cardsDto);
        return "cardsView";
    }
}
