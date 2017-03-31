package com.favendo.merchant.ws.rest.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.favendo.merchant.ws.rest.dto.MerchantAccountDto;
import com.favendo.user.service.domain.User;

@Component
public class MerchantAccountDtoConverter {

    public List<MerchantAccountDto> convert(List<User> merchants) {
        if (merchants != null) {
            final List<MerchantAccountDto> results = new ArrayList<>(merchants.size());
            merchants.stream().forEach(merchant-> {
                MerchantAccountDto result = convertMerchant(merchant);
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

    public MerchantAccountDto convertMerchant (User merchant) {
        if (merchant == null) {
            throw new IllegalArgumentException("Argument merchant cannot be null");
        }
        MerchantAccountDto merchantDto = new MerchantAccountDto();
        merchantDto.setUserId(merchant.getUser_id());
        merchantDto.setFirstname(merchant.getFirstName());
        merchantDto.setLastname(merchant.getLastName());
        merchantDto.setAccountNo(merchant.getAccountNo());
        merchantDto.setPhone(merchant.getPhone());
        merchantDto.setUsername(merchant.getUsername());
        merchantDto.setAccountName(merchant.getAccountName());
        return merchantDto;
    }
}
