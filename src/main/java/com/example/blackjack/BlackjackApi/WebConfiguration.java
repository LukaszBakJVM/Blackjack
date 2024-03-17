package com.example.blackjack.BlackjackApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfiguration {
    @Value("${baseUrl}")
    private String base;

    @Bean
    WebClient.Builder webClient() {
        return WebClient.builder().baseUrl(base);
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
