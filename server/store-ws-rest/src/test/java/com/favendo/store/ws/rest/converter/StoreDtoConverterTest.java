package com.favendo.store.ws.rest.converter;

import com.favendo.commons.domain.Store;
import com.favendo.store.ws.rest.convertor.StoreDtoConverter;
import com.favendo.store.ws.rest.dto.StoreDto;
import org.mockito.InjectMocks;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testng.MockitoTestNGListener;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Listeners(MockitoTestNGListener.class)
public class StoreDtoConverterTest {

    @InjectMocks
    private StoreDtoConverter subject;

    @Test
    public void buildStoreDto_WithAllInformation_BuildsAndReturnStoreDto() {
        String name = "Bear Flag Fish Co";
        String nickName = "Bear Flag HB";
        String managerOrPOC = "Julian Wilson";
        String phone = "+1-714-224-4938";
        String street = "454 PCH";
        String city = "Huntington Beach";
        String state = "Orange";
        String country = "US";
        String zipCode = "92646";
        String controllerNumber = "S1449";
        String controllerPlacement = "Center";
        String category = "Fast-Casual Restaurants";
        String subCategory = "Seafood";
        String priceCategory = "$$";
        String posSystem = "Lightspeed";
        String adminName = "Nathan Phillips";

        Store store = createStore(name, nickName, managerOrPOC, phone, street, city, state, country,
                zipCode, controllerNumber, controllerPlacement, category, subCategory,
                priceCategory, posSystem, adminName);

        StoreDto result = subject.buildStoreDto(store);
        StoreDto expected = createStoreDto(name, nickName, managerOrPOC, phone, street, city, state, country,
                zipCode, controllerNumber, controllerPlacement, category, subCategory,
                priceCategory, posSystem,  adminName);

        assertThat(result.getName(), is(expected.getName()));
        assertThat(result.getNickName(), is(expected.getNickName()));
        assertThat(result.getManagerOrPOC(), is(expected.getManagerOrPOC()));
        assertThat(result.getPhone(), is(expected.getPhone()));
        assertThat(result.getStreet(), is(expected.getStreet()));
        assertThat(result.getCity(), is(expected.getCity()));
        assertThat(result.getState(), is(expected.getState()));
        assertThat(result.getCountry(), is(expected.getCountry()));
        assertThat(result.getZipCode(), is(expected.getZipCode()));
        assertThat(result.getControllerNumber(), is(expected.getControllerNumber()));
        assertThat(result.getControllerPlacement(), is(expected.getControllerPlacement()));
        assertThat(result.getCategory(), is(expected.getCategory()));
        assertThat(result.getSubCategory(), is(expected.getSubCategory()));
        assertThat(result.getPriceCategory(), is(expected.getPriceCategory()));
        assertThat(result.getPosSystem(), is(expected.getPosSystem()));
        assertThat(result.getStorecastAdminName(), is(expected.getStorecastAdminName()));
    }

    private Store createStore(String name, String nickName,String managerOrPOC, String phone,
                              String street, String city, String state, String country, String zipCode, String controllerNumber,
                              String controllerPlacement,  String category, String subCategory,
                              String priceCategory, String posSystem,  String adminName) {
        Store store = new Store();
        store.setName(name);
        store.setNickName(nickName);
        store.setManagerOrPOC(managerOrPOC);
        store.setPhone(phone);
        store.setStreet(street);
        store.setCity(city);
        store.setState(state);
        store.setCountry(country);
        store.setZipCode(zipCode);
        store.setControllerNumber(controllerNumber);
        store.setControllerPlacement(controllerPlacement);
        store.setCategory(category);
        store.setSubCategory(subCategory);
        store.setPriceCategory(priceCategory);
        store.setPosSystem(posSystem);
        store.setStorecastAdminName(adminName);
        return store;
    }

    private StoreDto createStoreDto(String name, String nickName, String managerOrPOC, String phone,
                                    String street, String city, String state, String country, String zipCode, String controllerNumber,
                                    String controllerPlacement,  String category, String subCategory,
                                    String priceCategory, String posSystem,  String adminName) {
        StoreDto storeDto = new StoreDto();
        storeDto.setName(name);
        storeDto.setNickName(nickName);
        storeDto.setManagerOrPOC(managerOrPOC);
        storeDto.setPhone(phone);
        storeDto.setStreet(street);
        storeDto.setCity(city);
        storeDto.setState(state);
        storeDto.setCountry(country);
        storeDto.setZipCode(zipCode);
        storeDto.setControllerNumber(controllerNumber);
        storeDto.setControllerPlacement(controllerPlacement);
        storeDto.setCategory(category);
        storeDto.setSubCategory(subCategory);
        storeDto.setPriceCategory(priceCategory);
        storeDto.setPosSystem(posSystem);
        storeDto.setStorecastAdminName(adminName);
        return storeDto;
    }
}
