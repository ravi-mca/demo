package com.favendo.merchant.service.service;

import java.util.List;

import com.favendo.commons.exception.BusinessException;
import com.favendo.merchant.service.dto.MerchantDto;
import com.favendo.user.service.domain.User;

public interface MerchantService {

   List<User> getAll();

   User getByAccountNo(String accountNo);

   void save(MerchantDto merchantDto) throws BusinessException;

   void update(MerchantDto merchantDto, Long merchantId);

}