package com.favendo.store.service.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.favendo.commons.domain.Store;
import com.favendo.store.service.dao.StoreDao;
import com.favendo.store.service.service.StoreService;

@Service("storeService")
public class StoreServiceImpl implements StoreService {
    
    @Value("${invalid.merchant.id.error.message}")
    private String merchantIdNotNullErrorMessage;

    @Autowired
    private StoreDao storeDao;

    @Override
    public void save(Store store) {
        storeDao.save(store);
    }

    @Override
    public Store getById(Long storeId) {
        return storeDao.findOne(storeId);
    }

    @Override
    public Store getByNameOrNickName(String name, String nickName) {
        return storeDao.findByNameOrNickName(name, nickName);
    }

    @Override
    public Store getByNameOrNickNameAndId(String name, String nickName, Long id) {
        return storeDao.findByNameOrNickNameAndId(name, nickName, id);
    }
    
    @Override
    public List<Store> getAllByMerchantId(Long merchantId) {
        if (Objects.isNull(merchantId)) {
            throw new IllegalArgumentException(merchantIdNotNullErrorMessage);
        }
        return storeDao.findAllByMerchantId(merchantId);
    }
}
