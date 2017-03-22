package com.favendo.merchant.service.helper;

import com.favendo.commons.utils.UniqueIdGenerator;
import com.favendo.merchant.service.dto.MerchantDto;
import com.favendo.user.service.domain.User;
import org.springframework.stereotype.Component;

@Component
public class MerchantHelper {

    public User buildMerchant(MerchantDto merchantDto){
        User user = new User();
        user.setFirstName(merchantDto.getFirstName());
        user.setLastName(merchantDto.getLastName());
        user.setUsername(merchantDto.getEmail());
        user.setPhone(merchantDto.getPhone());
        user.setAccountNo(UniqueIdGenerator.generateUUID());
        user.setAccountName(merchantDto.getAccountName());
        return user;
    }
}
