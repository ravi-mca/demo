package com.favendo.store.service.service;

import java.util.List;

import com.favendo.commons.domain.Store;

public interface StoreService {

    void save(Store store);

    void delete(Long id);

    Store getById(Long id);

    Store getByNameOrNickName(String name,String nickName);

    Store getByNameOrNickNameAndId(String name,String nickName,Long id);
    
    List<Store> getAllByMerchantId(Long merchantId);
}
