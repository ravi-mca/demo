package com.favendo.merchant.service.helper;

import com.favendo.commons.utils.UniqueIdGenerator;
import com.favendo.merchant.service.dto.MerchantDto;
import com.favendo.user.service.domain.User;
import org.springframework.stereotype.Component;

@Component
public class MerchantHelper {

    public User buildMerchant(MerchantDto merchantDto){
        User storecastUser = new User();
        storecastUser.setFirstName(merchantDto.getFirstName());
        storecastUser.setLastName(merchantDto.getLastName());
        storecastUser.setUsername(merchantDto.getEmail());
        storecastUser.setPhone(merchantDto.getPhone());
        storecastUser.setAccountNo(UniqueIdGenerator.generateUUID());
        storecastUser.setAccountName(merchantDto.getAccountName());
        return storecastUser;
    }
}
