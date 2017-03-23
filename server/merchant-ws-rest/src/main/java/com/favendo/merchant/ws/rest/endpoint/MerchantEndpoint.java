package com.favendo.merchant.ws.rest.endpoint;

import static com.favendo.commons.utils.Routes.MERCHANT;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.favendo.commons.exception.BusinessException;
import com.favendo.commons.exception.ErrorKey;
import com.favendo.commons.exception.StorecastApiException;
import com.favendo.merchant.ws.rest.dto.converter.MerchantAccountDtoConverter;
import com.favendo.merchant.service.dto.MerchantDto;
import com.favendo.merchant.service.service.MerchantService;
import com.favendo.user.service.domain.User;

@Component
@Path(MERCHANT)
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class MerchantEndpoint {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantAccountDtoConverter merchantAccountDtoConverter;

    @POST
    @PermitAll
    public Response save(MerchantDto merchantDto) {
        try {
            merchantService.save(merchantDto);
            return Response.status(Status.CREATED).build();
        } catch (BusinessException exception) {
            throw new StorecastApiException(ErrorKey.SERVER_ERROR, exception.getMessage());
        }
    }

    @GET
    @PermitAll
    public Response getListOfMerchants() {
        try {
            List<User> merchants =  merchantService.getListOFMerchants();
            return Response.status(Status.ACCEPTED).entity(merchantAccountDtoConverter.convert(merchants)).build();
        } catch (BusinessException exception) {
            throw new StorecastApiException(ErrorKey.SERVER_ERROR, exception.getMessage());
        }
    }
}
