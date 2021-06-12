package com.example.peopleapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateItemException extends RuntimeException  {
    public DuplicateItemException(String message) {
        super(message);
    }
}
