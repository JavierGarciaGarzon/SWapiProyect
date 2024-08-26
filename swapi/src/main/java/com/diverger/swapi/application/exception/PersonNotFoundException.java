package com.diverger.swapi.application.exception;

import org.springframework.http.HttpStatus;

public class PersonNotFoundException extends SwapiException {
    public PersonNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
