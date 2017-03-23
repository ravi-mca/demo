package com.favendo.merchant.service.service;

import com.favendo.commons.exception.BusinessException;
import com.favendo.merchant.service.dto.MerchantDto;

public interface MerchantService {

    void save(MerchantDto merchantDto) throws BusinessException;

    void update(MerchantDto merchantDto,Long merchantId) throws BusinessException;
}
   