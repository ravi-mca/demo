package com.favendo.merchant.service.service;

import com.favendo.commons.exception.BusinessException;
import com.favendo.user.service.domain.User;

import java.util.List;

public interface MerchantService {

    List<User> getAll() throws BusinessException;

    User getByAccountNo(String accountNo) throws BusinessException;

    void save(User merchant) throws BusinessException;

    void update(User merchant) throws BusinessException;

}