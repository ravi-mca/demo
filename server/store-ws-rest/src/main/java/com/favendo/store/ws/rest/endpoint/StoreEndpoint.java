package com.favendo.store.ws.rest.endpoint;

import com.favendo.commons.domain.Merchant;
import com.favendo.commons.domain.Store;
import com.favendo.commons.exception.StorecastApiException;
import com.favendo.merchant.service.service.MerchantService;
import com.favendo.store.service.service.StoreService;
import com.favendo.store.ws.rest.builder.StoreBuilder;
import com.favendo.store.ws.rest.convertor.StoreDtoConverter;
import com.favendo.store.ws.rest.dto.StoreDto;
import com.favendo.store.ws.rest.validator.StoreValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

import static com.favendo.commons.exception.ErrorKey.BAD_REQUEST;
import static com.favendo.commons.utils.Routes.*;
import static com.favendo.user.service.constant.UserConstant.MERCHANT_ID;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path(CUSTOMER + STORE)
@Produces(value = {APPLICATION_JSON})
@Consumes(value = {APPLICATION_JSON})
public class StoreEndpoint {

    @Autowired
    private StoreService storeService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private StoreBuilder storeBuilder;

    @Autowired
    private StoreValidator storeValidator;

    @Autowired
    private StoreDtoConverter storeDtoConverter;

    @Value("${store.not.found.error.message}")
    private String storeNotFoundErrorMessage;

    @Value("${merchant.not.found.error.message}")
    private String merchantNotFoundErrorMessage;

    @POST
    @Path(PATH_PARAM_MERCHANT_ID)
    public Response save(@RequestBody StoreDto storeDto, @PathParam(MERCHANT_ID) Long merchantId) {
        Merchant merchant = getAndValidateMerchantById(merchantId);
        storeValidator.validateStore(storeDto);
        storeValidator.validateDuplication(storeDto, storeService.getByNameOrNickName(storeDto.getName(),
                storeDto.getNickName()));
        storeService.save(storeBuilder.buildStore(storeDto, merchant));
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path(PATH_PARAM_ID)
    public Response update(@RequestBody StoreDto storeDto, @PathParam(ID) Long id) {
        Store store = getAndValidateStore(id);
        storeValidator.validateZipCode(storeDto.getZipCode());
        storeValidator.validateDuplication(storeDto, storeService.getByNameOrNickNameAndId(storeDto.getName(),
                storeDto.getNickName(), id));
        storeService.save(storeBuilder.buildStore(storeDto, store));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path(PATH_PARAM_ID)
    public Response getById(@PathParam(ID) Long id) {
        return Response.ok().entity(storeDtoConverter.buildStoreDto(getAndValidateStore(id))).build();
    }
    
    @GET
    @Path(LIST + PATH_PARAM_MERCHANT_ID)
    public Response getAllByMerchantId(@PathParam(MERCHANT_ID) Long merchantId) {
        getAndValidateMerchantById(merchantId);
        List<Store> stores = storeService.getAllByMerchantId(merchantId);
        if (CollectionUtils.isEmpty(stores)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok().entity(storeDtoConverter.convertStores(stores)).build();
    }

    @DELETE
    @Path(PATH_PARAM_ID)
    public Response delete(@PathParam(ID) Long id) {
        getAndValidateStore(id);
        storeService.delete(id);
        return Response.status(Response.Status.OK).build();
    }

    private Merchant getAndValidateMerchantById(Long merchantId) {
        Merchant merchant = merchantService.getById(merchantId);
        if (Objects.isNull(merchant)) {
            throw new StorecastApiException(BAD_REQUEST, merchantNotFoundErrorMessage, MERCHANT_ID);
        }
        return merchant;
    }

    private Store getAndValidateStore(Long storeId) {
        Store store = storeService.getById(storeId);
        if (Objects.isNull(store)) {
            throw new StorecastApiException(BAD_REQUEST, storeNotFoundErrorMessage, ID);
        }
        return store;
    }
}
