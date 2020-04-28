package com.courses.management.common.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileNotFoundException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({Exception.class, FileNotFoundException.class})
    public ModelAndView handleException(Exception ex) {
        final ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex.getMessage());
        return modelAndView;
    }
}
