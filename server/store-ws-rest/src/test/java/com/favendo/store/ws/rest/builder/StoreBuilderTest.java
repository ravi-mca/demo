package com.favendo.store.ws.rest.builder;

import com.favendo.commons.domain.Store;
import com.favendo.commons.domain.User;
import com.favendo.store.ws.rest.builder.StoreBuilder;
import com.favendo.store.ws.rest.dto.StoreDto;
import org.mockito.InjectMocks;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testng.MockitoTestNGListener;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Listeners(MockitoTestNGListener.class)
public class StoreBuilderTest {

    @InjectMocks
    private StoreBuilder subject;

    @Test
    public void buildStore_WithAllDetails_BuildsAndReturnStore() {
        Long userId = 1L;
        String firstName = "Storecast";
        String lastName = "Storecast";
        String email = "storecast@gmail.com";
        String accountNo = "ABCD-EFGH-IJCK-LMOP";
        String accountName = "storecast_account_name";
        String name = "Bear Flag Fish Co";
        String nickName = "Bear Flag HB";
        String brandId = "N/A";
        String managerOrPOC = "Julian Wilson";
        String phone = "+1-714-224-4938";
        String street = "454 PCH";
        String city = "Huntington Beach";
        String state = "Orange";
        String country = "US";
        String zipCode = "92646";
        String controllerNumber = "S1449";
        String controllerPlacement = "Center";
        String wifiName = "N/A";
        String wifiPassword = "N/A";
        String category = "Fast-Casual Restaurants";
        String subCategory = "Seafood";
        String priceCategory = "$$";
        String posSystem = "Lightspeed";
        String loyaltyProgram = "N/A";
        String otherSystem = "N/A";
        String adminName = "Nathan Phillips";

        User user = createMerchant(userId, firstName, lastName, accountNo, accountName, phone, email);
        StoreDto storeDto = createStoreDto(name, nickName, brandId, managerOrPOC, phone, street, city, state, country,
                zipCode, controllerNumber, controllerPlacement, wifiName, wifiPassword, category, subCategory,
                priceCategory, posSystem, loyaltyProgram, otherSystem, adminName);

        Store result = subject.buildStore(storeDto, user);
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
        user.setAccountNo(accountNo);
        user.setAccountName(accountName);
        return user;
    }
}
