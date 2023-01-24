package com.team8.shop.tomatomarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiException handleApiRequestException(IllegalArgumentException ex) {
        return new RestApiException(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
}