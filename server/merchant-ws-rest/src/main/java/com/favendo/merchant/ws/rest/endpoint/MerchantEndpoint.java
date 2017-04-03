package com.favendo.merchant.ws.rest.endpoint;

import com.favendo.commons.exception.BusinessException;
import com.favendo.commons.exception.ErrorKey;
import com.favendo.commons.exception.StorecastApiException;
import com.favendo.merchant.ws.rest.dto.MerchantDto;
import com.favendo.merchant.ws.rest.helper.MerchantHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static com.favendo.commons.exception.ErrorKey.SERVER_ERROR;
import static com.favendo.commons.utils.Routes.*;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path(ADMIN + MERCHANT)
@Produces(value = {APPLICATION_JSON})
@Consumes(value = {APPLICATION_JSON})
public class MerchantEndpoint {

    @Autowired
    private MerchantHelper merchantHelper;

    @POST
    public Response save(@RequestBody MerchantDto merchantDto) {
        try {
            merchantHelper.save(merchantDto);
            return Response.status(Status.CREATED).build();
        } catch (BusinessException exception) {
            throw new StorecastApiException(ErrorKey.SERVER_ERROR, exception.getMessage());
        }
    }

    @PUT
    @Path(PATH_PARAM_MERCHANT_ID)
    public Response update(@RequestBody MerchantDto merchantDto, @PathParam(MERCHANT_ID) Long merchantId) {
        try {
            merchantHelper.update(merchantDto, merchantId);
            return Response.status(Status.OK).build();
        } catch (BusinessException exception) {
            throw new StorecastApiException(ErrorKey.SERVER_ERROR, exception.getMessage());
        }
    }

    @GET
    public Response getAll() {
        try {
            return Response.ok().entity(merchantHelper.getAll()).build();
        } catch (BusinessException exception) {
            throw new StorecastApiException(SERVER_ERROR, exception.getMessage());
        }
    }

    @GET
    @Path(PATH_PARAM_ACCOUNT_NO)
    public Response getByAccountNo(@PathParam(ACCOUNT_NO) String accountNo) {
        try {
            return Response.ok().entity(merchantHelper.getByAccountNo(accountNo)).build();
        } catch (BusinessException exception) {
            throw new StorecastApiException(ErrorKey.SERVER_ERROR, exception.getMessage());
        }
    }
}
