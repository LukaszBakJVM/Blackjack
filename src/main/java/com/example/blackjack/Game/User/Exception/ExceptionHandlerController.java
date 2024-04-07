package com.example.blackjack.Game.User.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(GameOverException.class)
    public void handleGameOverException(GameOverException e) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("errorMessage", e.getMessage());


    }

}
