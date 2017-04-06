package com.favendo.merchant.ws.rest.convertor;

import com.favendo.merchant.ws.rest.dto.MerchantDto;
import com.favendo.user.service.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MerchantDtoConverter {

    public List<MerchantDto> convertMerchants(List<User> merchants) {
        return merchants.stream().map(this::convertMerchant).collect(Collectors.toList());
    }

    public MerchantDto convertMerchant(User merchant) {
        MerchantDto merchantDto = new MerchantDto();
        convertBasicInformation(merchant, merchantDto);
        convertAccountInformation(merchant, merchantDto);
        return merchantDto;
    }

    private void convertBasicInformation(User merchant, MerchantDto merchantDto) {
        merchantDto.setUserId(merchant.getUser_id());
        merchantDto.setEmail(merchant.getUsername());
        merchantDto.setFirstName(merchant.getFirstName());
        merchantDto.setLastName(merchant.getLastName());
        merchantDto.setPhone(merchant.getPhone());
    }

    private void convertAccountInformation(User merchant, MerchantDto merchantDto) {
        merchantDto.setAccountNo(merchant.getUsername());
        merchantDto.setAccountName(merchant.getAccountName());
    }
}
