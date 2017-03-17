package com.favendo.commons.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    private static Logger LOG = LoggerFactory.getLogger(GenericExceptionMapper.class);

    public Response toResponse(Exception exception) {
        if (exception instanceof ForbiddenException) {
            LOG.info("Web Application Exception: " + exception);
            return (new StorecastApiException(exception, 403, "Access to the specified resource has been forbidden")).getResponse();
        } else if (exception instanceof WebApplicationException) {
            LOG.info("Web Application Exception: " + exception);
            return ((WebApplicationException) exception).getResponse();
        }

        LOG.error("Internal Server Error: " + exception);
        LOG.error("Internal Server Error: " + exception.getCause());

        if (!exception.getMessage().isEmpty()) {
            return (new StorecastApiException(exception, 500, exception.getMessage())).getResponse();
        }
        return (new StorecastApiException(exception, 500, "Oeppps ! Something went wrong")).getResponse();
    }
}