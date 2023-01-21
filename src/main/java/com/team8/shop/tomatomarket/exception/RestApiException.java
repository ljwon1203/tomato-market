package com.team8.shop.tomatomarket.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class RestApiException {
    private String errorMessage;
    private HttpStatus httpStatus;
}