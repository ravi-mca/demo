package com.favendo.merchant.rest.endpoint;

import com.favendo.commons.exception.BusinessException;
import com.favendo.commons.exception.ErrorKey;
import com.favendo.commons.exception.StorecastApiException;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.favendo.commons.utils.Routes.MERCHANT;

@Component
@Path(MERCHANT)
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class MerchantEndpoint {

    @GET
    @PermitAll
    public Response getMerchant() {
        try {
            return Response.ok().build();
        } catch (BusinessException exception) {
            throw new StorecastApiException(ErrorKey.SERVER_ERROR, exception.getMessage());
        }
    }
}
