package com.favendo.commons.exception;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static com.favendo.commons.exception.ErrorKey.FORBIDDEN;
import static com.favendo.commons.exception.ErrorKey.SERVER_ERROR;


public class StorecastExceptionMapper extends GenericExceptionMapper {

    private static Logger LOG = LoggerFactory.getLogger(StorecastExceptionMapper.class);

    private static final String INTERNAL_SERVER_ERROR = "something went wrong";

    private static final String ACCESS_FORBIDDEN = "access to specified resource is forbidd en";

    @Override
    public Response toResponse(Exception exception) {
        LOG.error(exception.getMessage(), exception);
        if (exception instanceof ForbiddenException) {
            return new StorecastApiException(FORBIDDEN, ACCESS_FORBIDDEN).getResponse();

        } else if (exception instanceof WebApplicationException) {
            return ((WebApplicationException) exception).getResponse();

        } else if (exception instanceof FieldViolationException) {
            FieldViolationException fieldViolationException = (FieldViolationException) exception;
            return new StorecastApiException(fieldViolationException.getErrorKey().getHttpStatus(),
                    fieldViolationException.getErrorKey().getErrorKey(), fieldViolationException.getMessageKey(),
                    fieldViolationException.getField()).getResponse();
        }

        return new StorecastApiException(SERVER_ERROR, getErrorMessage(exception)).getResponse();
    }

    private String getErrorMessage(Exception exception) {
        return StringUtils.isNotEmpty(exception.getMessage()) ? exception.getMessage() : INTERNAL_SERVER_ERROR;
    }
}
