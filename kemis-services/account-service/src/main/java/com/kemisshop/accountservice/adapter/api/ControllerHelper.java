package com.kemisshop.accountservice.adapter.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ControllerHelper {

    @ExceptionHandler( Exception.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String catchServiceImplementationErrors(Exception ex) {
        return ex.getMessage();
    }
}
