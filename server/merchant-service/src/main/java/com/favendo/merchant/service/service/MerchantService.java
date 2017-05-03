package com.favendo.merchant.service.service;

import com.favendo.commons.domain.Merchant;
import com.favendo.commons.domain.User;
import com.favendo.commons.exception.BusinessException;

import java.util.List;

public interface MerchantService {

    Merchant getById(Long id)throws BusinessException;

    Merchant getByAccountName(String accountName)throws BusinessException;

    Merchant getByAccountNameAndMerchantId(String accountName,Long merchantId)throws BusinessException;

    List<Merchant> getAll()throws BusinessException;

    Merchant getByAccountNo(String accountNo) throws BusinessException;

    void save(Merchant merchant,User user) throws BusinessException;

    void update(Merchant merchant,User user) throws BusinessException;

    void deleteById(Long userId);
}