package com.diverger.swapi.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SwapiException extends RuntimeException {
    private final HttpStatus status;

    public SwapiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
