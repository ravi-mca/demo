package com.favendo.store.service.service.impl;

import com.favendo.commons.domain.Store;
import com.favendo.store.service.dao.StoreDao;
import com.favendo.store.service.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("storeService")
public class StoreServiceImpl implements StoreService {

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
}
