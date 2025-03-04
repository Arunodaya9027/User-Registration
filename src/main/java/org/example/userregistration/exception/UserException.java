package org.example.userregistration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserException extends Exception {
    private final String message;

    public UserException(String message) {
        this.message = message;
    }
}
