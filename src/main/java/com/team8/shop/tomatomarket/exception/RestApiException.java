package com.team8.shop.tomatomarket.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class RestApiException {
    private final String errorMessage;
    private final HttpStatus httpStatus;
}