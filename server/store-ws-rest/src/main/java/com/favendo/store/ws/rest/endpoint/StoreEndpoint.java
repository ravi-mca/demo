package com.favendo.store.ws.rest.endpoint;

import static com.favendo.commons.exception.ErrorKey.BAD_REQUEST;
import static com.favendo.commons.utils.Routes.ADMIN;
import static com.favendo.commons.utils.Routes.LIST;
import static com.favendo.commons.utils.Routes.PATH_PARAM_MERCHANT_ID;
import static com.favendo.commons.utils.Routes.PATH_PARAM_STORE_ID;
import static com.favendo.commons.utils.Routes.STORE;
import static com.favendo.commons.utils.Routes.STORE_ID;
import static com.favendo.user.service.constant.UserConstant.MERCHANT_ID;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;
import java.util.Objects;

import javax.ws.rs.Consumes;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.favendo.commons.domain.Store;
import com.favendo.commons.domain.User;
import com.favendo.commons.exception.StorecastApiException;
import com.favendo.store.service.service.StoreService;
import com.favendo.store.ws.rest.builder.StoreBuilder;
import com.favendo.store.ws.rest.convertor.StoreDtoConverter;
import com.favendo.store.ws.rest.dto.StoreDto;
import com.favendo.store.ws.rest.validator.StoreValidator;
import com.favendo.user.service.service.UserService;

@Component
@Path(ADMIN + STORE)
@Produces(value = {APPLICATION_JSON})
@Consumes(value = {APPLICATION_JSON})
public class StoreEndpoint {

    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

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
        User user = getAndValidateUserByMerchantId(merchantId);
        storeValidator.validateStore(storeDto);
        storeValidator.validateDuplication(storeDto, storeService.getByNameOrNickName(storeDto.getName(),
                storeDto.getNickName()));
        storeService.save(storeBuilder.buildStore(storeDto, user));
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path(PATH_PARAM_STORE_ID)
    public Response update(@RequestBody StoreDto storeDto, @PathParam(STORE_ID) Long storeId) {
        Store store = getAndValidateStore(storeId);
        storeValidator.validateZipCode(storeDto.getZipCode());
        storeValidator.validateDuplication(storeDto, storeService.getByNameOrNickNameAndId(storeDto.getName(),
                storeDto.getNickName(), storeId));
        storeService.save(storeBuilder.buildStore(storeDto, store));
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path(PATH_PARAM_STORE_ID)
    public Response getById(@PathParam(STORE_ID) Long storeId) {
        return Response.ok().entity(storeDtoConverter.buildStoreDto(getAndValidateStore(storeId))).build();
    }
    
    @GET
    @Path(LIST + PATH_PARAM_MERCHANT_ID)
    public Response getAllByMerchantId(@PathParam(MERCHANT_ID) Long merchantId) {
        getAndValidateUserByMerchantId(merchantId);
        List<Store> stores = storeService.getAllByMerchantId(merchantId);
        if (CollectionUtils.isEmpty(stores)) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok().entity(storeDtoConverter.convertStores(stores)).build();
    }

    private User getAndValidateUserByMerchantId(Long merchantId) {
        User user = userService.getByUserId(merchantId);
        if (Objects.isNull(user)) {
            throw new StorecastApiException(BAD_REQUEST, merchantNotFoundErrorMessage, MERCHANT_ID);
        }
        return user;
    }

    private Store getAndValidateStore(Long storeId) {
        Store store = storeService.getById(storeId);
        if (Objects.isNull(store)) {
            throw new StorecastApiException(BAD_REQUEST, storeNotFoundErrorMessage, STORE_ID);
        }
        return store;
    }
}
