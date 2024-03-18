package com.example.blackjack.Game.Game;

import com.example.blackjack.BlackjackApi.CardsDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
public class GameController {
    private final WebClient webClient;
    // @Value("${cardsUrl}")
    private final String apiUrl = "http://localhost:8080/blackjack/";

    public GameController(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl(apiUrl).build();
    }

    @GetMapping("/show-cards/{id}")
    public Mono<String> showCards(@PathVariable String id, @RequestParam(defaultValue = "0") int count, Model model) {


        return webClient.get().uri(uriBuilder -> uriBuilder.path(id).queryParam("count", count).build()).retrieve().bodyToMono(CardsDto.class).doOnNext(cardsDto -> model.addAttribute("cardsDto", cardsDto)).thenReturn("cardsView");
    }

    @GetMapping("/")
    String start() {
        return "gameView";
    }
}
