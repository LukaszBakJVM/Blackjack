package com.example.blackjack.BlackjackApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfiguration {


    @Bean
    WebClient.Builder webClient() {
        return WebClient.builder();
    }

@Bean
    RestTemplate restTemplate (){
        return new RestTemplate();
}
}
