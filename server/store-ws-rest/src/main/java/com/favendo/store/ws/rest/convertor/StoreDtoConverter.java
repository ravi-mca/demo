package com.favendo.store.ws.rest.convertor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.favendo.commons.domain.Store;
import com.favendo.store.ws.rest.dto.StoreDto;

@Component
public class StoreDtoConverter {

    public StoreDto buildStoreDto(Store store) {
        StoreDto storeDto = new StoreDto();
        buildBasicInformation(store, storeDto);
        buildContactInformation(store, storeDto);
        buildControllerInformation(store, storeDto);
        buildCategoryInformation(store, storeDto);
        buildOtherInformation(store, storeDto);
        return storeDto;
    }
    
    public List<StoreDto> convertStores(List<Store> stores) {
        return stores.stream().map(this::convertStore).collect(Collectors.toList());
    }

    private StoreDto convertStore(Store store) {
        StoreDto storeDto = new StoreDto();
        storeDto.setId(store.getId());
        storeDto.setStoreId(store.getStoreId());
        storeDto.setName(store.getName());
        return storeDto;
    }

    private void buildBasicInformation(Store store, StoreDto storeDto) {
        storeDto.setStoreId(store.getStoreId());
        storeDto.setName(store.getName());
        storeDto.setNickName(store.getNickName());
        storeDto.setManagerOrPOC(store.getManagerOrPOC());
    }

    private void buildContactInformation(Store store, StoreDto storeDto) {
        storeDto.setPhone(store.getPhone());
        storeDto.setStreet(store.getStreet());
        storeDto.setCity(store.getCity());
        storeDto.setCountry(store.getCountry());
        storeDto.setState(store.getState());
        storeDto.setZipCode(store.getZipCode());
    }

    private void buildControllerInformation(Store store, StoreDto storeDto) {
        storeDto.setControllerNumber(store.getControllerNumber());
        storeDto.setControllerPlacement(store.getControllerPlacement());
    }

    private void buildCategoryInformation(Store store, StoreDto storeDto) {
        storeDto.setCategory(store.getCategory());
        storeDto.setSubCategory(store.getSubCategory());
        storeDto.setPriceCategory(store.getPriceCategory());
    }

    private void buildOtherInformation(Store store, StoreDto storeDto) {
        storeDto.setPosSystem(store.getPosSystem());
        storeDto.setStorecastAdminName(store.getStorecastAdminName());
    }
}
