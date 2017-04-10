package com.favendo.store.service.service;

import java.util.List;

import com.favendo.commons.domain.Store;

public interface StoreService {

    void save(Store store);

    Store getById(Long storeId);

    Store getByNameOrNickName(String name,String nickName);

    Store getByNameOrNickNameAndId(String name,String nickName,Long id);
    
    List<Store> getAllByMerchantId(Long storeId);
}
