package com.favendo.merchant.ws.rest.converter;

import com.favendo.commons.domain.User;
import com.favendo.merchant.ws.rest.convertor.MerchantDtoConverter;
import com.favendo.merchant.ws.rest.dto.MerchantDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MerchantAccountDtoConverterTest {

    private List<User> merchantList;

    @InjectMocks
    private MerchantDtoConverter merchantDtoConverter;

    private static final String MERCHANT1_FIRSTNAME = "Storecast";
    private static final String MERCHANT1_LASNAME = "Admin";
    private static final String MERCHANT1_USERNAME = "test-admin1@storecast.io";
    private static final Long MERCHANT1_USERID = 1l;
    private static final String MERCHANT1_ACCOUNT_NO = "ABCD1234";
    private static final String MERCHANT1_PHONE = "12345676";
    private static final String MERCHANT1_ACCOUNT_NAME = "Merchant1-Account";
    private static final String MERCHANT2_FIRSTNAME = "Storecast1";
    private static final String MERCHANT2_LASNAME = "Admin1";
    private static final String MERCHANT2_USERNAME = "test-admin2@storecast.io";
    private static final Long MERCHANT2_USERID = 2l;
    private static final String MERCHANT2_ACCOUNT_NO = "ABCD1235";
    private static final String MERCHANT2_PHONE = "12345676";
    private static final String MERCHANT2_ACCOUNT_NAME = "Merchant2-Account";

    @Before
    public void startup(){
        merchantList = buildMerchantList();
    }

    @Test
    public void testMerchantDtoConverter() {
        List<MerchantDto> merchantDtoList = merchantDtoConverter.convertMerchants(merchantList);
        Assert.assertNotNull(merchantDtoList);
        Assert.assertEquals(2, merchantDtoList.size());
        Assert.assertEquals("test-admin1@storecast.io", merchantDtoList.get(0).getEmail());
        Assert.assertEquals("ABCD1234", merchantDtoList.get(0).getAccountNo());
        Assert.assertEquals(2, merchantDtoList.get(1).getUserId().longValue());
        Assert.assertEquals("12345676", merchantDtoList.get(1).getPhone());
    }

    private List<User> buildMerchantList() {
        List<User> merchants = new ArrayList<>();
        User merchant1 = new User();
        merchant1.setAccountNo(MERCHANT1_ACCOUNT_NO);
        merchant1.setUser_id(MERCHANT1_USERID);
        merchant1.setUsername(MERCHANT1_USERNAME);
        merchant1.setFirstName(MERCHANT1_FIRSTNAME);
        merchant1.setLastName(MERCHANT1_LASNAME);
        merchant1.setPhone(MERCHANT1_PHONE);
        merchant1.setAccountName(MERCHANT1_ACCOUNT_NAME);

        User merchant2 = new User();
        merchant2.setAccountNo(MERCHANT2_ACCOUNT_NO);
        merchant2.setUser_id(MERCHANT2_USERID);
        merchant2.setUsername(MERCHANT2_USERNAME);
        merchant2.setFirstName(MERCHANT2_FIRSTNAME);
        merchant2.setLastName(MERCHANT2_LASNAME);
        merchant2.setPhone(MERCHANT2_PHONE);
        merchant2.setAccountName(MERCHANT2_ACCOUNT_NAME);

        merchants.add(merchant1);
        merchants.add(merchant2);
        return merchants;
    }
}
