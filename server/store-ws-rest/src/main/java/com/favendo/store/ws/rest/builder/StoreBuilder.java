package com.favendo.store.ws.rest.builder;

import com.favendo.commons.domain.Store;
import com.favendo.commons.domain.User;
import com.favendo.commons.utils.UniqueIdGenerator;
import com.favendo.store.ws.rest.dto.StoreDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class StoreBuilder {

    public Store buildStore(StoreDto storeDto, User user) {
        Store store = new Store();
        //store.setMerchant(user);
        store.setStoreId(UniqueIdGenerator.generateUUID());
        buildBasicInformation(storeDto, store);
        buildContactInformation(storeDto, store);
        buildControllerInformation(storeDto, store);
        buildCategoryInformation(storeDto, store);
        buildOtherInformation(storeDto, store);
        return store;
    }

    public Store buildStore(StoreDto storeDto, Store store) {
        buildBasicInformation(storeDto, store);
        buildContactInformation(storeDto, store);
        buildControllerInformation(storeDto, store);
        buildCategoryInformation(storeDto, store);
        buildOtherInformation(storeDto, store);
        return store;
    }

    private void buildBasicInformation(StoreDto storeDto, Store store) {
        if (StringUtils.isNotEmpty(storeDto.getName())) {
            store.setName(storeDto.getName());
        }
        if (StringUtils.isNotEmpty(storeDto.getNickName())) {
            store.setNickName(storeDto.getNickName());
        }
        if (StringUtils.isNotEmpty(storeDto.getBrandId())) {
            store.setBrandId(storeDto.getBrandId());
        }
        if (StringUtils.isNotEmpty(storeDto.getManagerOrPOC())) {
            store.setManagerOrPOC(storeDto.getManagerOrPOC());
        }
    }

    private void buildContactInformation(StoreDto storeDto, Store store) {
        if (StringUtils.isNotEmpty(storeDto.getPhone())) {
            store.setPhone(storeDto.getPhone());
        }
        if (StringUtils.isNotEmpty(storeDto.getStreet())) {
            store.setStreet(storeDto.getStreet());
        }
        if (StringUtils.isNotEmpty(storeDto.getCity())) {
            store.setCity(storeDto.getCity());
        }
        if (StringUtils.isNotEmpty(storeDto.getState())) {
            store.setState(storeDto.getState());
        }
        if (StringUtils.isNotEmpty(storeDto.getCountry())) {
            store.setCountry(storeDto.getCountry());
        }
        if (StringUtils.isNotEmpty(storeDto.getZipCode())) {
            store.setZipCode(storeDto.getZipCode());
        }
    }

    private void buildControllerInformation(StoreDto storeDto, Store store) {
        if (StringUtils.isNotEmpty(storeDto.getControllerNumber())) {
            store.setControllerNumber(storeDto.getControllerNumber());
        }
        if (StringUtils.isNotEmpty(storeDto.getControllerPlacement())) {
            store.setControllerPlacement(storeDto.getControllerPlacement());
        }
        if (StringUtils.isNotEmpty(storeDto.getWifiName())) {
            store.setWifiName(storeDto.getWifiName());
        }
        if (StringUtils.isNotEmpty(storeDto.getWifiPassword())) {
            store.setWifiPassword(storeDto.getWifiPassword());
        }
    }

    private void buildCategoryInformation(StoreDto storeDto, Store store) {
        if (StringUtils.isNotEmpty(storeDto.getCategory())) {
            store.setCategory(storeDto.getCategory());
        }
        if (StringUtils.isNotEmpty(storeDto.getSubCategory())) {
            store.setSubCategory(storeDto.getSubCategory());
        }
        if (StringUtils.isNotEmpty(storeDto.getPriceCategory())) {
            store.setPriceCategory(storeDto.getPriceCategory());
        }
    }

    private void buildOtherInformation(StoreDto storeDto, Store store) {
        if (StringUtils.isNotEmpty(storeDto.getPosSystem())) {
            store.setPosSystem(storeDto.getPosSystem());
        }
        if (StringUtils.isNotEmpty(storeDto.getLoyaltyProgram())) {
            store.setLoyaltyProgram(storeDto.getLoyaltyProgram());
        }
        if (StringUtils.isNotEmpty(storeDto.getOtherSystem())) {
            store.setOtherSystem(storeDto.getOtherSystem());
        }
        if (StringUtils.isNotEmpty(storeDto.getStorecastAdminName())) {
            store.setStorecastAdminName(storeDto.getStorecastAdminName());
        }
    }
}
