package com.favendo.merchant.service.service;

import com.favendo.commons.domain.Merchant;
import com.favendo.commons.domain.User;
import com.favendo.merchant.service.dao.MerchantDao;
import com.favendo.merchant.service.service.impl.MerchantServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static  org.junit.Assert.assertNotNull;
import static  org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MerchantServiceTest {

    @Mock
    private MerchantDao merchantDao;

    @InjectMocks
    private MerchantServiceImpl merchantService;

    private static final int MERCHANT_SIZE = 2;
    private static final int MERCHANT_USERS_SIZE = 1;
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

    @Test
    public void getAllMerchants_WithMerchantUsers_ReturnAllMerchantWithUsers() {
        List<Merchant> merchantList = buildMerchantList();
        Mockito.when(merchantDao.findAll()).thenReturn(merchantList);
        List<Merchant> merchants = merchantService.getAll();
        assertNotNull(merchants);
        assertEquals(MERCHANT_SIZE, merchants.size());
        assertEquals(MERCHANT1_ACCOUNT_NO, merchants.stream().findFirst().get().getAccountNo());
        assertEquals(MERCHANT_USERS_SIZE, merchants.stream().findFirst().get().getMerchantUsers().size());
    }

    @Test
    public void getMerchantAccountInfo_ReturnMerchantAccountInfo() {
        Mockito.when(merchantDao.findByAccountNo(MERCHANT1_ACCOUNT_NO)).thenReturn(buildFirstMerchant());
        Merchant merchant = merchantService.getByAccountNo(MERCHANT1_ACCOUNT_NO);
        assertNotNull(merchant);
        assertEquals(MERCHANT1_ACCOUNT_NO, merchant.getAccountNo());
    }

    private List<Merchant> buildMerchantList() {
        List<Merchant> merchants = new ArrayList<>();
        Merchant merchant1 = buildFirstMerchant();
        Merchant merchant2 = buildSecondMerchant();
        merchants.add(merchant1);
        merchants.add(merchant2);
        return merchants;
    }

    private Merchant buildFirstMerchant() {
        Merchant merchant = new Merchant();
        merchant.setAccountNo(MERCHANT1_ACCOUNT_NO);
        merchant.setAccountName(MERCHANT1_ACCOUNT_NAME);
        merchant.setMerchantUsers(buildFirstMerchantUsers());
        return merchant;
    }

    private Merchant buildSecondMerchant() {
        Merchant merchant = new Merchant();
        merchant.setAccountNo(MERCHANT2_ACCOUNT_NO);
        merchant.setAccountName(MERCHANT2_ACCOUNT_NAME);
        merchant.setMerchantUsers(buildSecondMerchantUsers());
        return merchant;
    }

    private List<User> buildFirstMerchantUsers() {
        List<User> merchantUsers = new ArrayList<>();
        merchantUsers.add(buildFirstMerchantUser());
        return merchantUsers;
    }

    private List<User> buildSecondMerchantUsers() {
        List<User> merchantUsers = new ArrayList<>();
        merchantUsers.add(buildSecondMerchantUser());
        return merchantUsers;
    }

    private User buildFirstMerchantUser() {
        User user = new User();
        user.setUser_id(MERCHANT1_USERID);
        user.setFirstName(MERCHANT1_FIRSTNAME);
        user.setLastName(MERCHANT1_LASNAME);
        user.setUsername(MERCHANT1_USERNAME);
        user.setPhone(MERCHANT1_PHONE);
        return user;
    }

    private User buildSecondMerchantUser() {
        User user = new User();
        user.setUser_id(MERCHANT2_USERID);
        user.setFirstName(MERCHANT2_FIRSTNAME);
        user.setLastName(MERCHANT2_LASNAME);
        user.setUsername(MERCHANT2_USERNAME);
        user.setPhone(MERCHANT2_PHONE);
        return user;
    }
}
