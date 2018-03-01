package com.prashanth.recipesapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberError(Exception exception){

        log.error("Number Format Exception Handling.");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("genericerrorpage");
        modelAndView.addObject("exception",exception);
        modelAndView.addObject("errorCode","400 Bad Request");
        return modelAndView;

    }
}
