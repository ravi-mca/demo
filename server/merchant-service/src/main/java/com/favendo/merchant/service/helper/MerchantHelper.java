package com.favendo.merchant.service.helper;

import com.favendo.commons.utils.UniqueIdGenerator;
import com.favendo.merchant.service.dto.MerchantDto;
import com.favendo.user.service.domain.Role;
import com.favendo.user.service.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MerchantHelper {

    public User buildMerchant(MerchantDto merchantDto, Role role) {
        User user = new User();
        user.setFirstName(merchantDto.getFirstName());
        user.setLastName(merchantDto.getLastName());
        user.setUsername(merchantDto.getEmail());
        user.setPhone(merchantDto.getPhone());
        user.setAccountNo(UniqueIdGenerator.generateUUID());
        user.setAccountName(merchantDto.getAccountName());
        user.setRoles(buildRoles(role));
        return user;
    }

    private List<Role> buildRoles(Role role) {
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }
}
