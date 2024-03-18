package com.example.blackjack.Game.Game;

import com.example.blackjack.BlackjackApi.CardsDto;
import com.example.blackjack.Game.User.PersonDto;
import com.example.blackjack.Game.User.PersonMapper;
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
    private final PersonMapper personMapper;
     @Value("${cardsUrl}")
    private  String apiUrl ;
    private int sum = 21;


    public GameController(RestTemplate restTemplate, PersonMapper personMapper) {
        this.restTemplate = restTemplate;
        this.personMapper = personMapper;
    }


    @GetMapping("/show-cards/{id}")
    public String showCards(@PathVariable String id, @RequestParam(defaultValue = "0") int count, Model model) {

        String apiEndpoint = apiUrl + id + "?count=" + count;

        CardsDto forObject = restTemplate.getForObject(apiEndpoint, CardsDto.class);
        PersonDto cardsDto = personMapper.map(forObject);


        model.addAttribute("cardsDto", cardsDto);
        return "cardsView";
    }

    @GetMapping("/")
    String start() {
        return "gameView";
    }
}
