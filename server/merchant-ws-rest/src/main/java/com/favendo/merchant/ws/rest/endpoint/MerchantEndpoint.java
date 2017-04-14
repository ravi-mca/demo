package com.favendo.merchant.ws.rest.endpoint;

import static com.favendo.commons.exception.ErrorKey.BAD_REQUEST;
import static com.favendo.commons.exception.ErrorKey.SERVER_ERROR;
import static com.favendo.commons.utils.Routes.ACCOUNT_NO;
import static com.favendo.commons.utils.Routes.ADMIN;
import static com.favendo.commons.utils.Routes.MERCHANT;
import static com.favendo.commons.utils.Routes.PATH_PARAM_ACCOUNT_NO;
import static com.favendo.commons.utils.Routes.PATH_PARAM_MERCHANT_ID;
import static com.favendo.user.service.constant.UserConstant.MERCHANT_ID;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.favendo.commons.domain.User;
import com.favendo.commons.exception.BusinessException;
import com.favendo.commons.exception.ErrorKey;
import com.favendo.commons.exception.StorecastApiException;
import com.favendo.merchant.service.service.MerchantService;
import com.favendo.merchant.ws.rest.builder.MerchantBuilder;
import com.favendo.merchant.ws.rest.convertor.MerchantDtoConverter;
import com.favendo.merchant.ws.rest.dto.MerchantDto;
import com.favendo.merchant.ws.rest.validator.MerchantValidator;
import com.favendo.user.service.service.UserService;

@Component
@Path(ADMIN + MERCHANT)
@Produces(value = {APPLICATION_JSON})
@Consumes(value = {APPLICATION_JSON})
public class MerchantEndpoint {

    @Autowired
    private UserService userService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantBuilder merchantBuilder;

    @Autowired
    private MerchantValidator merchantValidator;

    @Autowired
    private MerchantDtoConverter merchantDtoConverter;

    @Value("${merchant.not.found.error.message}")
    private String merchantNotFoundErrorMessage;

    @Value("${merchant.not.found.by.account.no.error.message}")
    private String merchantNotFoundByAccountNoErrorMessage;

    @GET
    public Response getAll() {
        try {
            return Response.ok().entity(merchantDtoConverter.convertMerchants(merchantService.getAll())).build();
        } catch (BusinessException exception) {
            throw new StorecastApiException(SERVER_ERROR, exception.getMessage());
        }
    }

    @GET
    @Path(PATH_PARAM_ACCOUNT_NO)
    public Response getByAccountNo(@PathParam(ACCOUNT_NO) String accountNo) {
        try {
            merchantValidator.validateAccountNo(accountNo);
            return Response.ok().entity(merchantDtoConverter.convertMerchant(merchantService
                    .getByAccountNo(accountNo))).build();
        } catch (BusinessException exception) {
            throw new StorecastApiException(ErrorKey.SERVER_ERROR, exception.getMessage());
        }
    }

    @POST
    public Response save(@RequestBody MerchantDto merchantDto) {
        try {
            merchantValidator.validateRequest(merchantDto);
            merchantValidator.validateDuplication(merchantDto, userService.getByUsernameOrAccountName(merchantDto.
                    getEmail(), merchantDto.getAccountName(), merchantDto.getFirstName()));
            merchantService.save(merchantBuilder.buildMerchant(merchantDto));
            return Response.status(Status.CREATED).build();
        } catch (BusinessException exception) {
            throw new StorecastApiException(ErrorKey.SERVER_ERROR, exception.getMessage());
        }
    }

    @PUT
    @Path(PATH_PARAM_MERCHANT_ID)
    public Response update(@RequestBody MerchantDto merchantDto, @PathParam(MERCHANT_ID) Long merchantId) {
        try {
            User user = getAndValidateUserByMerchantId(merchantId);
            merchantValidator.validateContactDetails(merchantDto.getEmail(), merchantDto.getPhone());
            merchantValidator.validateDuplication(merchantDto, userService.getByUsernameOrAccountNameAndUserId(merchantDto
                    .getEmail(), merchantDto.getAccountName(), user.getUser_id(), merchantDto.getFirstName()));
            merchantService.update(merchantBuilder.buildMerchant(merchantDto, user));
            return Response.status(Status.OK).build();
        } catch (BusinessException exception) {
            throw new StorecastApiException(ErrorKey.SERVER_ERROR, exception.getMessage());
        }
    }
    
    @DELETE
    @Path(PATH_PARAM_MERCHANT_ID)
    public Response deleteById(@PathParam(MERCHANT_ID) Long merchantId) {
        getAndValidateUserByMerchantId(merchantId);
        userService.deleteById(merchantId);
        return Response.status(Status.OK).build();
    }

    private User getAndValidateUserByMerchantId(Long merchantId) {
        User user = userService.getByUserId(merchantId);
        if (Objects.isNull(user)) {
            throw new StorecastApiException(BAD_REQUEST, merchantNotFoundErrorMessage, MERCHANT_ID);
        }
        return user;
    }
}
