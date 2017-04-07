package com.favendo.merchant.ws.rest.builder;

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

    public User buildMerchant(MerchantDto merchantDto) {
        User user = new User();
        user.setFirstName(merchantDto.getFirstName());
        user.setLastName(merchantDto.getLastName());
        user.setUsername(merchantDto.getEmail());
        user.setPhone(merchantDto.getPhone());
        user.setAccountNo(UniqueIdGenerator.generateUUID());
        user.setAccountName(merchantDto.getAccountName());
        return user;
    }

    public User buildMerchant(MerchantDto merchantDto, User user) {
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
        if (StringUtils.isNotEmpty(merchantDto.getAccountName())) {
            user.setAccountName(merchantDto.getAccountName());
        }
        return user;
    }

    private List<Role> buildRoles(Role role) {
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }
}
