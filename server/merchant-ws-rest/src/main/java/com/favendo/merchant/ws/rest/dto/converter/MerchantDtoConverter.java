package com.favendo.merchant.ws.rest.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.favendo.merchant.ws.rest.dto.MerchantDto;
import com.favendo.user.service.domain.User;

@Component
public class MerchantDtoConverter {

    public List<MerchantDto> convert(List<User> merchants) {
        if (merchants != null) {
            final List<MerchantDto> results = new ArrayList<>(merchants.size());
            merchants.stream().forEach(merchant-> {
                MerchantDto result = convertMerchant(merchant);
                if (result != null) {
                    results.add(result);
                }
            });
            if(results.size() > 0) {
                return results;
            }
        }
        return null;
    }

    public MerchantDto convertMerchant (User merchant) {
        if (merchant == null) {
            throw new IllegalArgumentException("Argument merchant cannot be null");
        }
        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setUserId(merchant.getUser_id());
        merchantDto.setFirstname(merchant.getFirstName());
        merchantDto.setLastname(merchant.getLastName());
        merchantDto.setAccountNo(merchant.getAccountNo());
        merchantDto.setPhone(merchant.getPhone());
        merchantDto.setUsername(merchant.getUsername());
        return merchantDto;
    }
}
