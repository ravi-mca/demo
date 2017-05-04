package com.favendo.merchant.ws.rest.builder;

import com.favendo.commons.domain.Customer;
import com.favendo.commons.domain.Merchant;
import com.favendo.commons.domain.User;
import com.favendo.commons.utils.UniqueIdGenerator;
import com.favendo.merchant.ws.rest.dto.MerchantDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

@Listeners(MockitoTestNGListener.class)
public class MerchantBuilderTest {

    @InjectMocks
    private MerchantBuilder subject;

    @Mock
    private UniqueIdGenerator uniqueIdGenerator;

    private static final String MERCHANT_FIRSTNAME = "Storecast";
    private static final String MERCHANT_LASNAME = "Admin";
    private static final String MERCHANT_USERNAME = "test-admin1@storecast.io";
    private static final String MERCHANT_PHONE = "12345676";
    private static final String MERCHANT_ACCOUNT_NO = "ABCD1235";
    private static final String MERCHANT_ACCOUNT_NAME = "Merchant2-Account";

    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "Customer";
    private static final String CUSTOMER_STREET = "SGVP";
    private static final String CUSTOMER_CITY = "Ahmedabad";
    private static final String CUSTOMER_STATE = "Gujarat";
    private static final String CUSTOMER_COUNTRY = "India";
    private static final String CUSTOMER_ZIPCODE = "382405";

    @Test
    public void buildMerchant_WithAllDetails_BuildAndReturnsMerchant() {
        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setAccountNo(MERCHANT_ACCOUNT_NO);
        merchantDto.setAccountName(MERCHANT_ACCOUNT_NAME);
        Customer customer = buildCustomer(CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_STREET,CUSTOMER_CITY,CUSTOMER_STATE,
                CUSTOMER_COUNTRY,CUSTOMER_ZIPCODE);

        Merchant result = subject.buildMerchant(merchantDto,customer);
        Merchant expected = buildMerchant(MERCHANT_ACCOUNT_NO, MERCHANT_ACCOUNT_NAME);

        assertNotNull(result.getAccountNo());
        assertNotNull(result.getCustomer());
        assertThat(result.getAccountName(), is(expected.getAccountName()));
    }

    @Test
    public void buildMerchantUser_WithAllDetails_BuildAndReturnsMerchantUser() {
        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setFirstName(MERCHANT_FIRSTNAME);
        merchantDto.setLastName(MERCHANT_LASNAME);
        merchantDto.setEmail(MERCHANT_USERNAME);
        merchantDto.setPhone(MERCHANT_PHONE);

        User result = subject.buildMerchantUser(merchantDto);
        User expected = buildMerchantUser(MERCHANT_FIRSTNAME, MERCHANT_LASNAME, MERCHANT_USERNAME, MERCHANT_PHONE);

        assertThat(result.getFirstName(), is(expected.getFirstName()));
        assertThat(result.getLastName(), is(expected.getLastName()));
        assertThat(result.getPhone(), is(expected.getPhone()));
        assertThat(result.getUsername(), is(expected.getUsername()));
    }

    @Test
    public void buildMerchant_WithAccountName_BuildAndReturnsMerchant() {
        Merchant merchant = new Merchant();
        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setAccountName(MERCHANT_ACCOUNT_NAME);

        Merchant result = subject.buildMerchant(merchantDto, merchant);
        Merchant expected = buildMerchant(MERCHANT_ACCOUNT_NAME);

        assertThat(result.getAccountName(), is(expected.getAccountName()));
    }

    @Test
    public void buildMerchantUser_WithMerchantAndAllDetails_BuildAndReturnsMerchantUser() {
        User user = new User();
        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setFirstName(MERCHANT_FIRSTNAME);
        merchantDto.setLastName(MERCHANT_LASNAME);
        merchantDto.setEmail(MERCHANT_USERNAME);
        merchantDto.setPhone(MERCHANT_PHONE);

        User result = subject.buildMerchantUser(merchantDto, user);
        User expected = buildMerchantUser(MERCHANT_FIRSTNAME, MERCHANT_LASNAME, MERCHANT_USERNAME, MERCHANT_PHONE);

        assertThat(result.getFirstName(), is(expected.getFirstName()));
        assertThat(result.getLastName(), is(expected.getLastName()));
        assertThat(result.getPhone(), is(expected.getPhone()));
        assertThat(result.getUsername(), is(expected.getUsername()));
    }

    private Merchant buildMerchant(String accountName) {
        Merchant merchant = new Merchant();
        merchant.setAccountName(accountName);
        return merchant;
    }

    private Merchant buildMerchant(String accountNo, String accountName) {
        Merchant merchant = new Merchant();
        merchant.setAccountNo(accountNo);
        merchant.setAccountName(accountName);
        return merchant;
    }

    private User buildMerchantUser(String firstName, String lastName, String username, String phone) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPhone(phone);
        return user;
    }

    private Customer buildCustomer(Long customerId,String name,String street,String city,String state, String country,
                                   String zipCode){
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setName(name);
        customer.setStreet(street);
        customer.setCity(city);
        customer.setState(state);
        customer.setCountry(country);
        customer.setZipcode(zipCode);
        return customer;
    }
}
