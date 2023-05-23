package com.estore.ecommercespring.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomException {
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ModelAndView customException(Exception ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("error", ex.getMessage());
        return modelAndView;
    }
}
