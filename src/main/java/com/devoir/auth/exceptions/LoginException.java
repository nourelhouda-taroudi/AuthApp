package com.devoir.auth.exceptions;

public class LoginException extends Exception {
    private static final long serialVersionUID = 1L;

    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    protected LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
