package com.favendo.user.ws.rest.endpoint;

import static com.favendo.commons.utils.Routes.CREDENTIALS;
import static com.favendo.commons.utils.Routes.USER;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.favendo.commons.domain.User;
import com.favendo.commons.exception.BusinessException;
import com.favendo.commons.exception.ErrorKey;
import com.favendo.commons.exception.StorecastApiException;
import com.favendo.user.service.utils.UserContextHolder;
import com.favendo.user.ws.rest.builder.UserDtoConverter;

@Component
@Path(USER)
@Produces(value = {APPLICATION_JSON})
@Consumes(value = {APPLICATION_JSON})
public class UserEndpoint {

    @Autowired
    private UserDtoConverter userDtoConverter;

    @GET
    @Path(CREDENTIALS)
    public Response getCredentials() {
        try {
            return Response.ok().entity(userDtoConverter.convertUserInfo(getLoogedInUser())).build();
        } catch (BusinessException exception) {
            throw new StorecastApiException(ErrorKey.SERVER_ERROR, exception.getMessage());
        }
    }

    private User getLoogedInUser() {
        return UserContextHolder.getLoggedInUser();
    }   
}
