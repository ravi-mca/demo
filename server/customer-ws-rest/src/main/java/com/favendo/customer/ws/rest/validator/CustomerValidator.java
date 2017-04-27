package com.favendo.customer.ws.rest.validator;

import static com.favendo.commons.exception.ErrorKey.BAD_REQUEST;
import static com.favendo.user.service.constant.UserConstant.CUSTOMER_CITY;
import static com.favendo.user.service.constant.UserConstant.CUSTOMER_COUNTRY;
import static com.favendo.user.service.constant.UserConstant.CUSTOMER_STATE;
import static com.favendo.user.service.constant.UserConstant.CUSTOMER_STREET;
import static com.favendo.user.service.constant.UserConstant.CUSTOMER_ZIP_CODE;
import static com.favendo.user.service.constant.UserConstant.EMAIL;
import static com.favendo.user.service.constant.UserConstant.FIRSTNAME;
import static com.favendo.user.service.constant.UserConstant.LASTNAME;
import static com.favendo.user.service.constant.UserConstant.PHONE;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.favendo.commons.validator.BooleanValidator;
import com.favendo.commons.validator.EmailValidator;
import com.favendo.commons.validator.EmptyOrNullValidator;
import com.favendo.commons.validator.PhoneValidator;
import com.favendo.commons.validator.ZipCodeValidator;
import com.favendo.customer.ws.rest.dto.CustomerDto;

@Component
public class CustomerValidator {

    @Autowired
    private PhoneValidator phoneValidator;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private BooleanValidator booleanValidator;
    
    @Autowired
    private ZipCodeValidator zipCodeValidator;

    @Autowired
    private EmptyOrNullValidator emptyOrNullValidator;
         
    @Value("${invalid.customer.request.error.message}")
    private String invalidCustomerRequestErrorMessage;

    @Value("${empty.customer.firstname.error.message}")
    private String emptyCustomerFirstNameErrorMessage;

    @Value("${empty.customer.lastname.error.message}")
    private String emptyCustomerLastNameErrorMessage;

    @Value("${empty.customer.email.error.message}")
    private String emptyCustomerEmailErrorMessage;

    @Value("${empty.customer.phone.error.message}")
    private String emptyCustomerPhoneErrorMessage;
    
    @Value("${empty.customer.street.error.message}")
    private String emptyCustomerStreetErrorMessage;
    
    @Value("${empty.customer.city.error.message}")
    private String emptyCustomerCityErrorMessage;
    
    @Value("${empty.customer.state.error.message}")
    private String emptyCustomerStateErrorMessage;
    
    @Value("${empty.customer.country.error.message}")
    private String emptyCustomerCountryErrorMessage;
    
    @Value("${empty.customer.zipcode.error.message}")
    private String emptyCustomerZipcodeErrorMessage;

    @Value("${invalid.customer.email.error.message}")
    private String invalidCustomerEmailErrorMessage;

    @Value("${invalid.customer.phone.error.message}")
    private String invalidCustomerPhoneErrorMessage;

    public void validateRequest(CustomerDto customerDto) {
        emptyOrNullValidator.validateIfNull(customerDto, BAD_REQUEST, invalidCustomerRequestErrorMessage);
        emptyOrNullValidator.validateFieldIfNull(customerDto.getFirstName(), BAD_REQUEST,emptyCustomerFirstNameErrorMessage, FIRSTNAME);
        emptyOrNullValidator.validateFieldIfNull(customerDto.getLastName(), BAD_REQUEST,emptyCustomerLastNameErrorMessage, LASTNAME);
        emptyOrNullValidator.validateFieldIfNull(customerDto.getEmail(), BAD_REQUEST,emptyCustomerEmailErrorMessage, EMAIL);
        emptyOrNullValidator.validateFieldIfNull(customerDto.getPhone(), BAD_REQUEST, emptyCustomerPhoneErrorMessage,PHONE);
        validateAddressInformation(customerDto);
        validateFormats(customerDto.getEmail(), customerDto.getPhone(), customerDto.getZipcode());
    }

    public void validateFormats(String email, String phone, String zipcode) {
        if (StringUtils.isNotEmpty(email)) {
            booleanValidator.validateIfFalse(emailValidator.validateEmail(email), BAD_REQUEST, invalidCustomerEmailErrorMessage, EMAIL);
        }
        if (StringUtils.isNotEmpty(phone)) {
            booleanValidator.validateIfFalse(phoneValidator.validatePhone(phone), BAD_REQUEST,invalidCustomerPhoneErrorMessage, PHONE);
        }
        if (StringUtils.isNotEmpty(zipcode)) {
            booleanValidator.validateIfFalse(zipCodeValidator.validateZipCode(zipcode), BAD_REQUEST,invalidCustomerPhoneErrorMessage, CUSTOMER_ZIP_CODE);
        }
    }
    
    private void validateAddressInformation(CustomerDto customerDto) {
        emptyOrNullValidator.validateFieldIfNull(customerDto.getStreet(), BAD_REQUEST, emptyCustomerStreetErrorMessage,CUSTOMER_STREET);
        emptyOrNullValidator.validateFieldIfNull(customerDto.getCity(), BAD_REQUEST, emptyCustomerCityErrorMessage,CUSTOMER_CITY);
        emptyOrNullValidator.validateFieldIfNull(customerDto.getState(), BAD_REQUEST, emptyCustomerStateErrorMessage,CUSTOMER_STATE);
        emptyOrNullValidator.validateFieldIfNull(customerDto.getCountry(), BAD_REQUEST, emptyCustomerCountryErrorMessage,CUSTOMER_COUNTRY);
        emptyOrNullValidator.validateFieldIfNull(customerDto.getZipcode(), BAD_REQUEST, emptyCustomerZipcodeErrorMessage,CUSTOMER_ZIP_CODE);
    }

}
