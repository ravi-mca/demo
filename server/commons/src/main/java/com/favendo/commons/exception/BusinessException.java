package com.favendo.commons.exception;

public class BusinessException extends RuntimeException {

    private int statusCode;

    private int errorCode;

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public BusinessException(int statusCode, int errorCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
