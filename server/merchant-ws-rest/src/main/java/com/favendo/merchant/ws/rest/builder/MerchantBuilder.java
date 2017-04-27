package com.favendo.merchant.ws.rest.builder;

import com.favendo.commons.domain.Customer;
import com.favendo.commons.domain.Merchant;
import com.favendo.commons.domain.Role;
import com.favendo.commons.domain.User;
import com.favendo.commons.utils.UniqueIdGenerator;
import com.favendo.merchant.ws.rest.dto.MerchantDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MerchantBuilder {

    public User buildMerchantUser(MerchantDto merchantDto) {
        User user = new User();
        user.setFirstName(merchantDto.getFirstName());
        user.setLastName(merchantDto.getLastName());
        user.setUsername(merchantDto.getEmail());
        user.setPhone(merchantDto.getPhone());
        return user;
    }

    public Merchant buildMerchant(MerchantDto merchantDto, Customer customer) {
        Merchant merchant = new Merchant();
        merchant.setAccountNo(UniqueIdGenerator.generateUUID());
        merchant.setAccountName(merchantDto.getAccountName());
        merchant.setCustomer(customer);
        return merchant;
    }

    public User buildMerchantUser(MerchantDto merchantDto, User user) {
        if (StringUtils.isNotEmpty(merchantDto.getFirstName())) {
            user.setFirstName(merchantDto.getFirstName());
        }
        if (StringUtils.isNotEmpty(merchantDto.getLastName())) {
            user.setLastName(merchantDto.getLastName());
        }
        if (StringUtils.isNotEmpty(merchantDto.getEmail())) {
            user.setUsername(merchantDto.getEmail());
        }
        if (StringUtils.isNotEmpty(merchantDto.getPhone())) {
            user.setPhone(merchantDto.getPhone());
        }
        return user;
    }

    public Merchant buildMerchant(MerchantDto merchantDto, Merchant merchant) {
        if (StringUtils.isNotBlank(merchantDto.getAccountName())) {
            merchant.setAccountName(merchantDto.getAccountName());
        }
        return merchant;
    }
}
