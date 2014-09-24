package com.iteam.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordMismatchException extends RuntimeException {

    private static final long serialVersionUID = 83129L;

    @Override
    public String getMessage() {
        return "The passwords don't match.";
    }
}
