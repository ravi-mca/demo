package com.favendo.commons.exception;

public class FieldViolationException extends RuntimeException {

    private ErrorKey errorKey;

    private String messageKey;

    private String field;

    public FieldViolationException(ErrorKey errorKey, String messageKey, String field) {
        this.errorKey = errorKey;
        this.messageKey = messageKey;
        this.field = field;
    }

    public FieldViolationException(ErrorKey errorKey, String messageKey) {
        this.errorKey = errorKey;
        this.messageKey = messageKey;
    }

    public FieldViolationException(String messageKey, String field) {
        this.messageKey = messageKey;
        this.field = field;
    }

    public FieldViolationException(String message) {
        this.messageKey = message;
    }

    public ErrorKey getErrorKey() {
        return errorKey;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public String getField() {
        return field;
    }
}
