package com.favendo.commons.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Objects;

public class StorecastApiException extends WebApplicationException {

    protected int status;

    protected Throwable cause;

    protected String description;

    public StorecastApiException(final int status, final String errorType) {
        super(Response.status(status).entity(new StorecastApiExceptionWrapper(errorType)).build());
    }

    public StorecastApiException(final int status, final String errorType, final String errorDescription) {
        super(Response.status(status).entity(new StorecastApiExceptionWrapper(errorType, errorDescription)).build());
        this.description = errorDescription;
    }

    public StorecastApiException(final int status, final String errorType, final String errorDescription,
                                 final String errorField) {
        super(Response.status(status).entity(new StorecastApiExceptionWrapper(errorType, errorDescription, errorField))
                .build());
        this.description = errorDescription;
    }

    public StorecastApiException(final ErrorKey errorKey, final String errorDescription, final String errorField) {
        super(Response.status(Objects.nonNull(errorKey.getStatus()) ? errorKey.getStatus().getStatusCode() : errorKey.getHttpStatus())
                .entity(new StorecastApiExceptionWrapper(errorKey.getErrorKey(), errorDescription, errorField)).build());
        this.description = errorDescription;
    }

    public StorecastApiException(final Throwable cause, final int status, final String errorType,
                                 final String errorDescription) {
        super(cause,
                Response.status(status).entity(new StorecastApiExceptionWrapper(errorType, errorDescription)).build());
    }

    public StorecastApiException(final ErrorKey errorKey, final String errorDescription, final String code,
                                 final String message) {
        super(Response.status(errorKey.getStatus())
                .entity(new StorecastApiExceptionWrapper(errorKey.getErrorKey(), errorDescription, code, message))
                .build());
        this.description = errorDescription;
    }

    public StorecastApiException(final ErrorKey errorKey, final String errorDescription) {
        this(errorKey, errorDescription, null);
    }

    public StorecastApiException(final ErrorKey errorKey) {
        this(errorKey, null, null);
    }

    public StorecastApiException(Throwable cause, int status, String message) {
        this(cause, status, "ApiException", message);
    }
}
