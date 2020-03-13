package com.epam.telephonedirectory.controller;

import com.epam.telephonedirectory.exception.NotFoundException;
import com.epam.telephonedirectory.exception.PdfCreationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@ControllerAdvice
public class AdviceController {

    @ExceptionHandler({NotFoundException.class, EmptyResultDataAccessException.class})
    public ModelAndView notFoundException(Exception e) {
        log(e);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        modelAndView.addObject("status", HttpStatus.BAD_REQUEST.getReasonPhrase());
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("cause", e.getCause());
        modelAndView.setViewName("notFound");
        return modelAndView;
    }

    @ExceptionHandler(PdfCreationException.class)
    public ModelAndView notFoundException(PdfCreationException e) {
        log.info("Message = {}, Cause= {}", e.getMessage(), e.getCause());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        modelAndView.addObject("status", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("cause", e.getCause());
        modelAndView.setViewName("pdfException");
        return modelAndView;
    }


    private void log(Exception e) {
        log.error("Message = {}, Cause= {}", e.getMessage(), e.getCause());
    }
}
