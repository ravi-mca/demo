package com.favendo.store.ws.rest.builder;

import com.favendo.commons.domain.Customer;
import com.favendo.commons.domain.Merchant;
import com.favendo.commons.domain.Store;
import com.favendo.commons.domain.User;
import com.favendo.store.ws.rest.dto.StoreDto;
import org.mockito.InjectMocks;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testng.MockitoTestNGListener;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Listeners(MockitoTestNGListener.class)
public class StoreBuilderTest {

    private static final Long MERCHANT_ID = 1L;
    private static final String ACCOUNT_NO = "ABCD-EFGH-IJCK-LMOP";
    private static final String ACCOUNT_NAME = "storecast_account_name";
    private static final String STORE_NAME = "Bear Flag Fish Co";
    private static final String STORE_NICKNAME = "Bear Flag HB";
    private static final String STORE_BRAND_ID = "N/A";
    private static final String STORE_MANAGER_POC = "Julian Wilson";
    private static final String PHONE = "+1-714-224-4938";
    private static final String STREET = "454 PCH";
    private static final String CITY = "Huntington Beach";
    private static final String STATE = "Orange";
    private static final String COUNTRY = "US";
    private static final String ZIPCODE = "92646";
    private static final String CONTROLLER_NUMBER = "S1449";
    private static final String CONTROLLER_PLACEMENT = "Center";
    private static final String WIFI_NAME = "N/A";
    private static final String WIFI_PASSWORD = "N/A";
    private static final String CATEGORY = "Fast-Casual Restaurants";
    private static final String SUB_CATEGORY = "Seafood";
    private static final String PRICE_CATEGORY = "$$";
    private static final String POS_SYSTEM = "Lightspeed";
    private static final String LOYALTY_PROGRAM = "N/A";
    private static final String OTHER_SYSTEM = "N/A";
    private static final String ADMIN_NAME = "Nathan Phillips";

    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "Customer";
    private static final String CUSTOMER_STREET = "SGVP";
    private static final String CUSTOMER_CITY = "Ahmedabad";
    private static final String CUSTOMER_STATE = "Gujarat";
    private static final String CUSTOMER_COUNTRY = "India";
    private static final String CUSTOMER_ZIPCODE = "382405";

    @InjectMocks
    private StoreBuilder subject;

    @Test
    public void buildStore_WithMerchantAndAllDetails_ReturnStore() {
        Merchant merchant = buildMerchant(MERCHANT_ID, ACCOUNT_NO, ACCOUNT_NAME);
        StoreDto storeDto = createStoreDto(STORE_NAME, STORE_NICKNAME, STORE_BRAND_ID, STORE_MANAGER_POC, PHONE, STREET,
                CITY, STATE, COUNTRY, ZIPCODE, CONTROLLER_NUMBER, CONTROLLER_PLACEMENT, WIFI_NAME, WIFI_PASSWORD, CATEGORY,
                SUB_CATEGORY, PRICE_CATEGORY, POS_SYSTEM, LOYALTY_PROGRAM, OTHER_SYSTEM, ADMIN_NAME);

        Store result = subject.buildStore(storeDto, merchant);
        Store expected = createStore(storeDto);

        assertThat(result.getName(), is(expected.getName()));
        assertThat(result.getNickName(), is(expected.getNickName()));
        assertThat(result.getBrandId(), is(expected.getBrandId()));
        assertThat(result.getManagerOrPOC(), is(expected.getManagerOrPOC()));
        assertThat(result.getPhone(), is(expected.getPhone()));
        assertThat(result.getStreet(), is(expected.getStreet()));
        assertThat(result.getCity(), is(expected.getCity()));
        assertThat(result.getState(), is(expected.getState()));
        assertThat(result.getCountry(), is(expected.getCountry()));
        assertThat(result.getZipCode(), is(expected.getZipCode()));
        assertThat(result.getControllerNumber(), is(expected.getControllerNumber()));
        assertThat(result.getControllerPlacement(), is(expected.getControllerPlacement()));
        assertThat(result.getWifiName(), is(expected.getWifiName()));
        assertThat(result.getWifiPassword(), is(expected.getWifiPassword()));
        assertThat(result.getCategory(), is(expected.getCategory()));
        assertThat(result.getSubCategory(), is(expected.getSubCategory()));
        assertThat(result.getPriceCategory(), is(expected.getPriceCategory()));
        assertThat(result.getPosSystem(), is(expected.getPosSystem()));
        assertThat(result.getLoyaltyProgram(), is(expected.getLoyaltyProgram()));
        assertThat(result.getOtherSystem(), is(expected.getOtherSystem()));
        assertThat(result.getStorecastAdminName(), is(expected.getStorecastAdminName()));
    }

    @Test
    public void buildStore_WithAllDetails_ReturnStore() {
        Store store = new Store();
        StoreDto storeDto = createStoreDto(STORE_NAME, STORE_NICKNAME, STORE_BRAND_ID, STORE_MANAGER_POC, PHONE, STREET,
                CITY, STATE, COUNTRY, ZIPCODE, CONTROLLER_NUMBER, CONTROLLER_PLACEMENT, WIFI_NAME, WIFI_PASSWORD, CATEGORY,
                SUB_CATEGORY, PRICE_CATEGORY, POS_SYSTEM, LOYALTY_PROGRAM, OTHER_SYSTEM, ADMIN_NAME);

        Store result = subject.buildStore(storeDto, store);
        Store expected = createStore(storeDto);

        assertThat(result.getName(), is(expected.getName()));
        assertThat(result.getNickName(), is(expected.getNickName()));
        assertThat(result.getBrandId(), is(expected.getBrandId()));
        assertThat(result.getManagerOrPOC(), is(expected.getManagerOrPOC()));
        assertThat(result.getPhone(), is(expected.getPhone()));
        assertThat(result.getStreet(), is(expected.getStreet()));
        assertThat(result.getCity(), is(expected.getCity()));
        assertThat(result.getState(), is(expected.getState()));
        assertThat(result.getCountry(), is(expected.getCountry()));
        assertThat(result.getZipCode(), is(expected.getZipCode()));
        assertThat(result.getControllerNumber(), is(expected.getControllerNumber()));
        assertThat(result.getControllerPlacement(), is(expected.getControllerPlacement()));
        assertThat(result.getWifiName(), is(expected.getWifiName()));
        assertThat(result.getWifiPassword(), is(expected.getWifiPassword()));
        assertThat(result.getCategory(), is(expected.getCategory()));
        assertThat(result.getSubCategory(), is(expected.getSubCategory()));
        assertThat(result.getPriceCategory(), is(expected.getPriceCategory()));
        assertThat(result.getPosSystem(), is(expected.getPosSystem()));
        assertThat(result.getLoyaltyProgram(), is(expected.getLoyaltyProgram()));
        assertThat(result.getOtherSystem(), is(expected.getOtherSystem()));
        assertThat(result.getStorecastAdminName(), is(expected.getStorecastAdminName()));
    }

    private StoreDto createStoreDto(String name, String nickName, String brandId, String managerOrPOC, String phone,
                                    String street, String city, String state, String country, String zipCode, String controllerNumber,
                                    String controllerPlacement, String wifiName, String wifiPassword, String category, String subCategory,
                                    String priceCategory, String posSystem, String loyaltyProgram, String otherSystem, String storecastAdminName) {
        StoreDto storeDto = new StoreDto();
        storeDto.setName(name);
        storeDto.setNickName(nickName);
        storeDto.setBrandId(brandId);
        storeDto.setManagerOrPOC(managerOrPOC);
        storeDto.setPhone(phone);
        storeDto.setStreet(street);
        storeDto.setCity(city);
        storeDto.setState(state);
        storeDto.setCountry(country);
        storeDto.setZipCode(zipCode);
        storeDto.setControllerNumber(controllerNumber);
        storeDto.setControllerPlacement(controllerPlacement);
        storeDto.setWifiName(wifiName);
        storeDto.setWifiPassword(wifiPassword);
        storeDto.setCategory(category);
        storeDto.setSubCategory(subCategory);
        storeDto.setPriceCategory(priceCategory);
        storeDto.setPosSystem(posSystem);
        storeDto.setLoyaltyProgram(loyaltyProgram);
        storeDto.setOtherSystem(otherSystem);
        storeDto.setLoyaltyProgram(loyaltyProgram);
        return storeDto;
    }

    private Store createStore(StoreDto storeDto) {
        Store store = new Store();
        store.setName(storeDto.getName());
        store.setNickName(storeDto.getNickName());
        store.setBrandId(storeDto.getBrandId());
        store.setManagerOrPOC(storeDto.getManagerOrPOC());
        store.setPhone(storeDto.getPhone());
        store.setStreet(storeDto.getStreet());
        store.setCity(storeDto.getCity());
        store.setState(storeDto.getState());
        store.setCountry(storeDto.getCountry());
        store.setZipCode(storeDto.getZipCode());
        store.setControllerNumber(storeDto.getControllerNumber());
        store.setControllerPlacement(storeDto.getControllerPlacement());
        store.setWifiName(storeDto.getWifiName());
        store.setWifiPassword(storeDto.getWifiPassword());
        store.setCategory(storeDto.getCategory());
        store.setSubCategory(storeDto.getSubCategory());
        store.setPriceCategory(storeDto.getPriceCategory());
        store.setPosSystem(storeDto.getPosSystem());
        store.setLoyaltyProgram(storeDto.getLoyaltyProgram());
        store.setOtherSystem(storeDto.getOtherSystem());
        store.setStorecastAdminName(storeDto.getStorecastAdminName());
        return store;
    }

    private User createMerchant(Long userId, String firstName, String lastName, String accountNo, String accountName,
                                String phone, String email) {
        User user = new User();
        user.setUser_id(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(email);
        user.setPhone(phone);
        //user.setAccountNo(accountNo);
        //user.setAccountName(accountName);
        return user;
    }

    private Merchant buildMerchant(Long merchantId, String accountNo, String accountName) {
        Merchant merchant = new Merchant();
        Customer customer = buildCustomer(CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_STREET, CUSTOMER_CITY, CUSTOMER_STATE,
                CUSTOMER_COUNTRY, CUSTOMER_ZIPCODE);
        merchant.setMerchantId(merchantId);
        merchant.setAccountNo(accountNo);
        merchant.setAccountName(accountName);
        merchant.setCustomer(customer);
        return merchant;
    }

    private Customer buildCustomer(Long customerId, String name, String street, String city, String state, String country,
                                   String zipCode) {
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
