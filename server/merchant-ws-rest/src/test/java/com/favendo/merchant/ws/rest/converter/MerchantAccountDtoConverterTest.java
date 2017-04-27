package com.favendo.merchant.ws.rest.converter;

import com.favendo.commons.domain.Merchant;
import com.favendo.commons.domain.User;
import com.favendo.merchant.ws.rest.convertor.MerchantDtoConverter;
import com.favendo.merchant.ws.rest.dto.MerchantDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import static  org.junit.Assert.assertNotNull;
import static  org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MerchantAccountDtoConverterTest {

    @InjectMocks
    private MerchantDtoConverter merchantDtoConverter;

    private static final Long MERCHANT_USERID = 1l;
    private static final String MERCHANT_FIRSTNAME = "Storecast";
    private static final String MERCHANT_LASNAME = "Admin";
    private static final String MERCHANT_USERNAME = "test-admin1@storecast.io";
    private static final String MERCHANT_PHONE = "12345676";
    private static final String MERCHANT_ACCOUNT_NO = "ABCD1234";
    private static final String MERCHANT_ACCOUNT_NAME = "Merchant1-Account";

    @Test
    public void buildMerchantDto_WithAllDetails_ReturnsMerchantDto() {
        List<Merchant> merchantList = buildMerchantList();
        List<MerchantDto> merchantDtoList = merchantDtoConverter.convertMerchants(merchantList);
        assertNotNull(merchantDtoList);
        assertEquals(MERCHANT_USERNAME, merchantDtoList.stream().findFirst().get().getEmail());
        assertEquals(MERCHANT_ACCOUNT_NO, merchantDtoList.stream().findFirst().get().getAccountNo());
        assertEquals(MERCHANT_ACCOUNT_NAME, merchantDtoList.stream().findFirst().get().getAccountName());
        assertEquals(MERCHANT_FIRSTNAME, merchantDtoList.stream().findFirst().get().getFirstName());
        assertEquals(MERCHANT_LASNAME,merchantDtoList.stream().findFirst().get().getLastName());
        assertEquals(MERCHANT_PHONE,merchantDtoList.stream().findFirst().get().getPhone());
    }

    private List<Merchant> buildMerchantList() {
        List<Merchant> merchants = new ArrayList<>();
        Merchant merchant = buildMerchant();
        merchant.setMerchantUsers(buildMerchantUsers());
        merchants.add(merchant);
        return merchants;
    }

    private Merchant buildMerchant(){
        Merchant merchant = new Merchant();
        merchant.setAccountNo(MERCHANT_ACCOUNT_NO);
        merchant.setAccountName(MERCHANT_ACCOUNT_NAME);
        return merchant;
    }

    private List<User> buildMerchantUsers(){
        List<User> merchantUsers = new ArrayList<>();
        merchantUsers.add(buildMerchantUser());
        return merchantUsers;
    }

    private User buildMerchantUser() {
        User user = new User();
        user.setUser_id(MERCHANT_USERID);
        user.setUsername(MERCHANT_USERNAME);
        user.setFirstName(MERCHANT_FIRSTNAME);
        user.setLastName(MERCHANT_LASNAME);
        user.setPhone(MERCHANT_PHONE);
        return user;
    }
}
