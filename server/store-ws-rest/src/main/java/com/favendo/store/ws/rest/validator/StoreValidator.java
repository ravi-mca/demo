package com.favendo.store.ws.rest.validator;

import com.favendo.commons.domain.Store;
import com.favendo.commons.validator.BooleanValidator;
import com.favendo.commons.validator.EmptyOrNullValidator;
import com.favendo.commons.validator.EqualsValidator;
import com.favendo.commons.validator.ZipCodeValidator;
import com.favendo.store.ws.rest.dto.StoreDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.favendo.commons.exception.ErrorKey.ALREADY_EXISTS;
import static com.favendo.commons.exception.ErrorKey.BAD_REQUEST;
import static com.favendo.store.ws.rest.constant.StoreConstant.*;

@Component
public class StoreValidator {

    @Autowired
    private EqualsValidator equalsValidator;

    @Autowired
    private BooleanValidator booleanValidator;

    @Autowired
    private ZipCodeValidator zipCodeValidator;

    @Autowired
    private EmptyOrNullValidator emptyOrNullValidator;

    @Value("${empty.store.name.error.message}")
    private String emptyStoreNameErrorMessage;

    @Value("${empty.store.nickname.error.message}")
    private String emptyStoreNickNameErrorMessage;

    @Value("${empty.store.brand.id.error.message}")
    private String emptyStoreBrandIdErrorMessage;

    @Value("${empty.store.manager.poc.error.message}")
    private String emptyStoreManagerOrPOCErrorMessage;

    @Value("${empty.store.phone.error.message}")
    private String emptyStorePhoneErrorMessage;

    @Value("${empty.store.street.error.message}")
    private String emptyStoreStreetErrorMessage;

    @Value("${empty.store.city.error.message}")
    private String emptyStoreCityErrorMessage;

    @Value("${empty.store.state.error.message}")
    private String emptyStoreStateErrorMessage;

    @Value("${empty.store.country.error.message}")
    private String emptyStoreCountryErrorMessage;

    @Value("${empty.store.zip.code.error.message}")
    private String emptyStoreZipCodeErrorMessage;

    @Value("${empty.store.controller.number.error.message}")
    private String emptyStoreControllerNumberErrorMessage;

    @Value("${empty.store.controller.placement.error.message}")
    private String emptyStoreControllerPlacementErrorMessage;

    @Value("${empty.store.wifi.name.error.message}")
    private String emptyStoreWifiNameErrorMessage;

    @Value("${empty.store.wifi.password.error.message}")
    private String emptyStoreWifiPasswordErrorMessage;

    @Value("${empty.store.category.error.message}")
    private String emptyStoreCategoryErrorMessage;

    @Value("${empty.store.sub.category.error.message}")
    private String emptyStoreSubCategoryErrorMessage;

    @Value("${empty.store.pos.system.error.message}")
    private String emptyStorePosSystemErrorMessage;

    @Value("${empty.store.loyalty.program.error.message}")
    private String emptyStoreLoyaltyProgramErrorMessage;

    @Value("${empty.store.price.category.error.message}")
    private String emptyStorePriceCategoryErrorMessage;

    @Value("${empty.store.other.system.error.message}")
    private String emptyStoreOtherSystemErrorMessage;

    @Value("${empty.store.admin.name.error.message}")
    private String emptyStoreAdminNameErrorMessage;

    @Value("${invalid.merchant.phone.error.message}")
    private String invalidPhoneErrorMessage;

    @Value("${invalid.store.zipcode.error.message}")
    private String invalidStoreZipCodeErrorMessage;

    @Value("${duplicate.store.name.error.message}")
    private String duplicateStoreNameErrorMessage;

    @Value("${duplicate.store.nickname.error.message}")
    private String duplicateStoreNickNameErrorMessage;

    public void validateStore(StoreDto storeDto) {
        validateBasicInformation(storeDto);
        validateContactInformation(storeDto);
        validateControllerInformation(storeDto);
        validateCategoryInformation(storeDto);
        validateFormats(storeDto);
    }

    public void validateDuplication(StoreDto storeDto, Store store) {
        if (Objects.nonNull(store)) {
            equalsValidator.validateIfEqualsIgnoreCase(storeDto.getName(), store.getName(), ALREADY_EXISTS,
                    duplicateStoreNameErrorMessage);
            equalsValidator.validateIfEqualsIgnoreCase(storeDto.getNickName(), store.getNickName(), ALREADY_EXISTS,
                    duplicateStoreNickNameErrorMessage);
        }
    }

    public void validateZipCode(String zipCode) {
        if (StringUtils.isNotEmpty(zipCode)) {
            booleanValidator.validateIfFalse(zipCodeValidator.validateZipCode(zipCode), BAD_REQUEST,
                    invalidStoreZipCodeErrorMessage, STORE_ZIP_CODE);
        }
    }

    private void validateBasicInformation(StoreDto storeDto) {
        emptyOrNullValidator.validateFieldIfNull(storeDto.getName(), BAD_REQUEST, emptyStoreNameErrorMessage, STORE_NAME);
        emptyOrNullValidator.validateFieldIfNull(storeDto.getNickName(), BAD_REQUEST, emptyStoreNickNameErrorMessage,
                STORE_NICKNAME);
        emptyOrNullValidator.validateFieldIfNull(storeDto.getManagerOrPOC(), BAD_REQUEST,
                emptyStoreManagerOrPOCErrorMessage, STORE_MANAGER_OR_POC);
    }

    private void validateContactInformation(StoreDto storeDto) {
        emptyOrNullValidator.validateFieldIfNull(storeDto.getPhone(), BAD_REQUEST, emptyStorePhoneErrorMessage,
                STORE_PHONE_NUMBER);
        emptyOrNullValidator.validateFieldIfNull(storeDto.getStreet(), BAD_REQUEST, emptyStoreStreetErrorMessage,
                STORE_STREET);
        emptyOrNullValidator.validateFieldIfNull(storeDto.getCity(), BAD_REQUEST, emptyStoreCityErrorMessage,
                STORE_CITY);
        emptyOrNullValidator.validateFieldIfNull(storeDto.getState(), BAD_REQUEST, emptyStoreStateErrorMessage,
                STORE_STATE);
        emptyOrNullValidator.validateFieldIfNull(storeDto.getCountry(), BAD_REQUEST, emptyStoreCountryErrorMessage,
                STORE_COUNTRY);
        emptyOrNullValidator.validateFieldIfNull(storeDto.getZipCode(), BAD_REQUEST, emptyStoreZipCodeErrorMessage,
                STORE_ZIP_CODE);
    }

    private void validateControllerInformation(StoreDto storeDto) {
        emptyOrNullValidator.validateFieldIfNull(storeDto.getControllerNumber(), BAD_REQUEST,
                emptyStoreControllerNumberErrorMessage, STORE_CONTROLLER_NUMBER);
        emptyOrNullValidator.validateFieldIfNull(storeDto.getControllerPlacement(), BAD_REQUEST,
                emptyStoreControllerPlacementErrorMessage, STORE_CONTROLLER_PLACEMENT);
    }

    private void validateCategoryInformation(StoreDto storeDto) {
        emptyOrNullValidator.validateFieldIfNull(storeDto.getCategory(), BAD_REQUEST, emptyStoreCategoryErrorMessage,
                STORE_CATEGORY);
        emptyOrNullValidator.validateFieldIfNull(storeDto.getSubCategory(), BAD_REQUEST,
                emptyStoreSubCategoryErrorMessage, STORE_SUB_CATEGORY);
        emptyOrNullValidator.validateFieldIfNull(storeDto.getPriceCategory(), BAD_REQUEST,
                emptyStorePriceCategoryErrorMessage, STORE_PRICE_CATEGORY);
    }

    private void validateFormats(StoreDto storeDto) {
        booleanValidator.validateIfFalse(zipCodeValidator.validateZipCode(storeDto.getZipCode()), BAD_REQUEST,
                invalidStoreZipCodeErrorMessage, STORE_ZIP_CODE);
    }
}
