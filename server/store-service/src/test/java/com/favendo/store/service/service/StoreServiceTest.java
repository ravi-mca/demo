package com.favendo.store.service.service;

import com.favendo.commons.domain.Store;
import com.favendo.store.service.dao.StoreDao;
import com.favendo.store.service.service.impl.StoreServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testng.MockitoTestNGListener;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@Listeners(MockitoTestNGListener.class)
public class StoreServiceTest {

    @InjectMocks
    private StoreServiceImpl subject;

    @Mock
    private StoreDao storeDao;

    @Test
    public void getStore_BuildAndReturnsStore() {
        Long storeId = 114L;
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

        Store store = createStore(name, nickName, brandId, managerOrPOC, phone, street, city, state, country,
                zipCode, controllerNumber, controllerPlacement, wifiName, wifiPassword, category, subCategory,
                priceCategory, posSystem, loyaltyProgram, otherSystem, adminName);

        when(storeDao.findOne(storeId)).thenReturn(store);

        Store result = subject.getById(storeId);
        Store expected = createStore(name, nickName, brandId, managerOrPOC, phone, street, city, state, country,
                zipCode, controllerNumber, controllerPlacement, wifiName, wifiPassword, category, subCategory,
                priceCategory, posSystem, loyaltyProgram, otherSystem, adminName);

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
    public void getStoreByNameOrNickName_BuildAndReturnsStore() {
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

        Store store = createStore(name, nickName, brandId, managerOrPOC, phone, street, city, state, country,
                zipCode, controllerNumber, controllerPlacement, wifiName, wifiPassword, category, subCategory,
                priceCategory, posSystem, loyaltyProgram, otherSystem, adminName);

        when(storeDao.findByNameOrNickName(name,nickName)).thenReturn(store);

        Store result = subject.getByNameOrNickName(name,nickName);
        Store expected = createStore(name, nickName, brandId, managerOrPOC, phone, street, city, state, country,
                zipCode, controllerNumber, controllerPlacement, wifiName, wifiPassword, category, subCategory,
                priceCategory, posSystem, loyaltyProgram, otherSystem, adminName);

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
    public void getStoreByNameOrNickNameAndId_BuildAndReturnsStore() {
        Long id = 1L;
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

        Store store = createStore(name, nickName, brandId, managerOrPOC, phone, street, city, state, country,
                zipCode, controllerNumber, controllerPlacement, wifiName, wifiPassword, category, subCategory,
                priceCategory, posSystem, loyaltyProgram, otherSystem, adminName);

        when(storeDao.findByNameOrNickNameAndId(name,nickName,id)).thenReturn(store);

        Store result = subject.getByNameOrNickNameAndId(name,nickName,id);
        Store expected = createStore(name, nickName, brandId, managerOrPOC, phone, street, city, state, country,
                zipCode, controllerNumber, controllerPlacement, wifiName, wifiPassword, category, subCategory,
                priceCategory, posSystem, loyaltyProgram, otherSystem, adminName);

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

    private Store createStore(String name, String nickName, String brandId, String managerOrPOC, String phone,
                              String street, String city, String state, String country, String zipCode, String controllerNumber,
                              String controllerPlacement, String wifiName, String wifiPassword, String category, String subCategory,
                              String priceCategory, String posSystem, String loyaltyProgram, String otherSystem, String adminName) {
        Store store = new Store();
        store.setName(name);
        store.setNickName(nickName);
        store.setBrandId(brandId);
        store.setManagerOrPOC(managerOrPOC);
        store.setPhone(phone);
        store.setStreet(street);
        store.setCity(city);
        store.setState(state);
        store.setCountry(country);
        store.setZipCode(zipCode);
        store.setControllerNumber(controllerNumber);
        store.setControllerPlacement(controllerPlacement);
        store.setWifiName(wifiName);
        store.setWifiPassword(wifiPassword);
        store.setCategory(category);
        store.setSubCategory(subCategory);
        store.setPriceCategory(priceCategory);
        store.setPosSystem(posSystem);
        store.setLoyaltyProgram(loyaltyProgram);
        store.setOtherSystem(otherSystem);
        store.setStorecastAdminName(adminName);
        return store;
    }
}
