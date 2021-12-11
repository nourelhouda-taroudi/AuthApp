package com.devoir.auth.exceptions;

public class InvalidJwtTokenException extends Exception {
    public InvalidJwtTokenException() {
        super();
    }

    public InvalidJwtTokenException(String message) {
        super(message);
    }
}
