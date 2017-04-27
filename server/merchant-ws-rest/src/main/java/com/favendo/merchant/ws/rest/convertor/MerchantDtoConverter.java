package com.favendo.merchant.ws.rest.convertor;

import com.favendo.commons.domain.Merchant;
import com.favendo.commons.domain.User;
import com.favendo.merchant.ws.rest.dto.MerchantDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MerchantDtoConverter {

    public List<MerchantDto> convertMerchants(List<Merchant> merchants) {
        return merchants.stream().map(this::convertMerchant).collect(Collectors.toList());
    }

    public MerchantDto convertMerchant(Merchant merchant) {
        MerchantDto merchantDto = new MerchantDto();
        Optional<User> optional = merchant.getMerchantUsers().stream().findFirst();
        convertBasicInformation(optional.get(), merchantDto);
        convertAccountInformation(merchant, merchantDto);
        return merchantDto;
    }

    private void convertBasicInformation(User user, MerchantDto merchantDto) {
        merchantDto.setEmail(user.getUsername());
        merchantDto.setFirstName(user.getFirstName());
        merchantDto.setLastName(user.getLastName());
        merchantDto.setPhone(user.getPhone());
    }

    private void convertAccountInformation(Merchant merchant, MerchantDto merchantDto) {
        merchantDto.setMerchantId(merchant.getMerchantId());
        merchantDto.setAccountNo(merchant.getAccountNo());
        merchantDto.setAccountName(merchant.getAccountName());
    }
}
