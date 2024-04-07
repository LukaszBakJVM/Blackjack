package com.example.blackjack.Game.User.Exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(GameOverException.class)
    public String handleGameOverException(GameOverException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "GameOver";

    }
}
