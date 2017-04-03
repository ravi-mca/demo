package com.favendo.merchant.service.service.impl;

import com.favendo.user.service.domain.User;
import com.favendo.user.service.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MerchantServiceTest {

    private List<User> merchantList;

    @Mock
    private UserService userService;

    @InjectMocks
    private MerchantServiceImpl merchantService;

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
    public void startup() {
        merchantList = buildMerchantList();
    }

    @Test
    public void testGetMerchantList() {
        Mockito.when(userService.getAll()).thenReturn(merchantList);
        List<User> users = merchantService.getAll();
        Assert.assertNotNull(users);
        Assert.assertEquals(2, users.size());
        Assert.assertEquals("test-admin1@storecast.io", users.get(0).getUsername());
    }

    @Test
    public void testGetMerchantAccountInfo() {
        Mockito.when(userService.getByAccountNo(MERCHANT1_ACCOUNT_NO)).thenReturn(buildFirstMerchant());
        User merchant = userService.getByAccountNo(MERCHANT1_ACCOUNT_NO);
        Assert.assertNotNull(merchant);
        Assert.assertEquals("test-admin1@storecast.io", merchant.getUsername());
        Assert.assertEquals("12345676", merchant.getPhone());
    }

    private List<User> buildMerchantList() {
        List<User> merchants = new ArrayList<>();
        User merchant1 = buildFirstMerchant();
        User merchant2 = buildSecondMerchant();
        merchants.add(merchant1);
        merchants.add(merchant2);
        return merchants;
    }

    private User buildFirstMerchant() {
        User merchant1 = new User();
        merchant1.setAccountNo(MERCHANT1_ACCOUNT_NO);
        merchant1.setUser_id(MERCHANT1_USERID);
        merchant1.setUsername(MERCHANT1_USERNAME);
        merchant1.setFirstName(MERCHANT1_FIRSTNAME);
        merchant1.setLastName(MERCHANT1_LASNAME);
        merchant1.setPhone(MERCHANT1_PHONE);
        merchant1.setAccountName(MERCHANT1_ACCOUNT_NAME);
        return merchant1;
    }

    private User buildSecondMerchant() {
        User merchant2 = new User();
        merchant2.setAccountNo(MERCHANT2_ACCOUNT_NO);
        merchant2.setUser_id(MERCHANT2_USERID);
        merchant2.setUsername(MERCHANT2_USERNAME);
        merchant2.setFirstName(MERCHANT2_FIRSTNAME);
        merchant2.setLastName(MERCHANT2_LASNAME);
        merchant2.setPhone(MERCHANT2_PHONE);
        merchant2.setAccountName(MERCHANT2_ACCOUNT_NAME);
        return merchant2;
    }
}
