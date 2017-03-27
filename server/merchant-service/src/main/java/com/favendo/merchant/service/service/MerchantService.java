package com.favendo.merchant.service.service;

import java.util.List;

import com.favendo.commons.exception.BusinessException;
import com.favendo.merchant.service.dto.MerchantDto;
import com.favendo.user.service.domain.User;

public interface MerchantService {

   void save(MerchantDto merchantDto) throws BusinessException;

   void update(MerchantDto merchantDto,Long merchantId) throws BusinessException;
   
   List<User> getListOFMerchants();
}