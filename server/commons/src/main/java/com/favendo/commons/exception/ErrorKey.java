package com.favendo.commons.exception;

import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.Response;

public enum ErrorKey {

    ALREADY_EXISTS("already_exists", Response.Status.CONFLICT),
    FORBIDDEN("forbidden", Response.Status.FORBIDDEN),
    NOT_FOUND("not_found", Response.Status.NOT_FOUND),
    UNAUTHORIZED("unauthorized", Response.Status.UNAUTHORIZED),
    SERVER_ERROR("server_error", Response.Status.INTERNAL_SERVER_ERROR),
    BAD_REQUEST("bad_request", Response.Status.BAD_REQUEST),
    NO_CONTENT("no_content", Response.Status.NO_CONTENT);

    private String errorKey;

    private Response.Status status;

    private int httpStatus;

    ErrorKey(final String errorKey, final Response.Status status) {
        this.errorKey = errorKey;
        this.status = status;
    }

    ErrorKey(final String errorKey, final int httpStatus) {
        this.errorKey = errorKey;
        this.httpStatus = httpStatus;
    }

    public String getErrorKey() {
        return this.errorKey;
    }

    public Response.Status getStatus() {
        return this.status;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public static ErrorKey fromErrorKey(final String errorKey) {
        if (!StringUtils.isBlank(errorKey)) {
            for (ErrorKey key : ErrorKey.values()) {
                if (errorKey.equalsIgnoreCase(key.errorKey)) {
                    return key;
                }
            }
        }
        return null;
    }
}
