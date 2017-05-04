package com.favendo.merchant.ws.rest.endpoint;

import com.favendo.commons.domain.Customer;
import com.favendo.commons.domain.Merchant;
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
import com.favendo.user.service.utils.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.Objects;
import java.util.Optional;

import static com.favendo.commons.exception.ErrorKey.BAD_REQUEST;
import static com.favendo.commons.exception.ErrorKey.SERVER_ERROR;
import static com.favendo.commons.utils.Routes.*;
import static com.favendo.user.service.constant.UserConstant.MERCHANT_ID;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path(CUSTOMER + MERCHANT)
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
            return Response.ok().entity(merchantDtoConverter.convertMerchant(merchantService.
                    getByAccountNo(accountNo))).build();
        } catch (BusinessException exception) {
            throw new StorecastApiException(ErrorKey.SERVER_ERROR, exception.getMessage());
        }
    }

    @POST
    public Response save(@RequestBody MerchantDto merchantDto) {
        try {
            validateSaveRequest(merchantDto);
            merchantService.save(merchantBuilder.buildMerchant(merchantDto, getCurrentCustomer()), merchantBuilder.
                    buildMerchantUser(merchantDto));
            return Response.status(Status.CREATED).build();
        } catch (BusinessException exception) {
            throw new StorecastApiException(ErrorKey.SERVER_ERROR, exception.getMessage());
        }
    }

    @PUT
    @Path(PATH_PARAM_MERCHANT_ID)
    public Response update(@RequestBody MerchantDto merchantDto, @PathParam(MERCHANT_ID) Long merchantId) {
        try {
            Merchant merchant = getAndValidateMerchantById(merchantId);
            Optional<User> optional = merchant.getMerchantUsers().stream().findFirst();
            validateUpdateRequest(merchantDto, merchant);
            merchantService.update(merchantBuilder.buildMerchant(merchantDto, merchant), merchantBuilder.
                    buildMerchantUser(merchantDto, optional.get()));
            return Response.status(Status.OK).build();
        } catch (BusinessException exception) {
            throw new StorecastApiException(ErrorKey.SERVER_ERROR, exception.getMessage());
        }
    }

    @DELETE
    @Path(PATH_PARAM_MERCHANT_ID)
    public Response deleteById(@PathParam(MERCHANT_ID) Long merchantId) {
        getAndValidateMerchantById(merchantId);
        merchantService.deleteById(merchantId);
        return Response.status(Status.OK).build();
    }

    private Merchant getAndValidateMerchantById(Long merchantId) {
        Merchant merchant = merchantService.getById(merchantId);
        if (Objects.isNull(merchant)) {
            throw new StorecastApiException(BAD_REQUEST, merchantNotFoundErrorMessage, MERCHANT_ID);
        }
        return merchant;
    }

    private void validateSaveRequest(@RequestBody MerchantDto merchantDto) {
        merchantValidator.validateRequest(merchantDto);
        merchantValidator.validateDuplication(merchantService.getByAccountName(merchantDto.getAccountName()));
        merchantValidator.validateDuplication(userService.getByUsername(merchantDto.getEmail()));
    }

    private void validateUpdateRequest(@RequestBody MerchantDto merchantDto, Merchant merchant) {
        merchantValidator.validateContactDetails(merchantDto.getEmail(), merchantDto.getPhone());
        merchantValidator.validateDuplication(merchantService.getByAccountNameAndMerchantId(merchantDto.getAccountName(),
                merchant.getMerchantId()));
        merchantValidator.validateDuplication(userService.getByUsernameAndUserId(merchantDto.getEmail(),
                merchant.getMerchantUsers().stream().findFirst().get().getUser_id()));
    }

    private Customer getCurrentCustomer() {
        return UserContextHolder.getLoggedInUser().getCustomer();
    }
}
