package com.favendo.merchant.service.service;

import java.util.List;

import com.favendo.commons.exception.BusinessException;
import com.favendo.user.service.domain.User;

public interface MerchantService {

   void save(User merchant) throws BusinessException;

   List<User> getAll();
   
   User getByAccountNo(String accountNo) throws BusinessException;
}