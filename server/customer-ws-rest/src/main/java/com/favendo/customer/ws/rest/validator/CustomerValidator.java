package com.favendo.customer.ws.rest.validator;

import com.favendo.commons.domain.Customer;
import com.favendo.commons.domain.User;
import com.favendo.commons.validator.*;
import com.favendo.customer.ws.rest.dto.CustomerDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.favendo.commons.exception.ErrorKey.ALREADY_EXISTS;
import static com.favendo.commons.exception.ErrorKey.BAD_REQUEST;
import static com.favendo.customer.ws.rest.constant.CustomerConstant.*;
import static com.favendo.user.service.constant.UserConstant.*;

@Component
public class CustomerValidator {

    @Autowired
    private PhoneValidator phoneValidator;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private EqualsValidator equalsValidator;

    @Autowired
    private ZipCodeValidator zipCodeValidator;

    @Autowired
    private BooleanValidator booleanValidator;

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

    @Value("${empty.customer.name.error.message}")
    private String emptyCustomerNameErrorMessage;

    @Value("${empty.customer.street.error.message}")
    private String emptyCustomerStreetErrorMessage;

    @Value("${empty.customer.city.error.message}")
    private String emptyCustomerCityErrorMessage;

    @Value("${empty.customer.state.error.message}")
    private String emptyCustomerStateErrorMessage;

    @Value("${empty.customer.country.error.message}")
    private String emptyCustomerCountryErrorMessage;

    @Value("${empty.customer.zipcode.error.message}")
    private String emptyCustomerZipCodeErrorMessage;

    @Value("${invalid.customer.email.error.message}")
    private String invalidCustomerEmailErrorMessage;

    @Value("${invalid.customer.email.error.message}")
    private String invalidCustomerPhoneErrorMessage;

    @Value("${invalid.customer.zipcode.error.message}")
    private String invalidCustomerZipCodeErrorMessage;

    @Value("${duplicate.customer.name.error.message}")
    private String duplicateCustomerNameErrorMessage;

    @Value("${duplicate.customer.firstname.error.message}")
    private String duplicateCustomerFirstNameErrorMessage;

    @Value("${duplicate.customer.email.error.message}")
    private String duplicateCustomerEmailErrorMessage;

    public void validateRequest(CustomerDto customerDto) {
        emptyOrNullValidator.validateIfNull(customerDto, BAD_REQUEST, invalidCustomerRequestErrorMessage);
        emptyOrNullValidator.validateFieldIfNull(customerDto.getFirstName(), BAD_REQUEST,
                emptyCustomerFirstNameErrorMessage, FIRSTNAME);
        emptyOrNullValidator.validateFieldIfNull(customerDto.getLastName(), BAD_REQUEST,
                emptyCustomerLastNameErrorMessage, LASTNAME);
        emptyOrNullValidator.validateFieldIfNull(customerDto.getEmail(), BAD_REQUEST,
                emptyCustomerEmailErrorMessage, PHONE);
        emptyOrNullValidator.validateFieldIfNull(customerDto.getPhone(), BAD_REQUEST,
                emptyCustomerPhoneErrorMessage, EMAIL);
        emptyOrNullValidator.validateFieldIfNull(customerDto.getName(), BAD_REQUEST,
                emptyCustomerNameErrorMessage, NAME);
        emptyOrNullValidator.validateFieldIfNull(customerDto.getStreet(), BAD_REQUEST,
                emptyCustomerStreetErrorMessage, STREET);
        emptyOrNullValidator.validateFieldIfNull(customerDto.getCity(), BAD_REQUEST,
                emptyCustomerCityErrorMessage, CITY);
        emptyOrNullValidator.validateFieldIfNull(customerDto.getState(), BAD_REQUEST,
                emptyCustomerStateErrorMessage, STATE);
        emptyOrNullValidator.validateFieldIfNull(customerDto.getCountry(), BAD_REQUEST,
                emptyCustomerCountryErrorMessage, COUNTRY);
        emptyOrNullValidator.validateFieldIfNull(customerDto.getZipcode(), BAD_REQUEST,
                emptyCustomerZipCodeErrorMessage, ZIPCODE);
        validateZipCode(customerDto.getZipcode());
        validateContactDetails(customerDto.getEmail(), customerDto.getPhone());
    }

    public void validateContactDetails(String email, String phone) {
        if (StringUtils.isNotEmpty(email)) {
            booleanValidator.validateIfFalse(emailValidator.validateEmail(email), BAD_REQUEST,
                    invalidCustomerEmailErrorMessage, EMAIL);
        }
        if (StringUtils.isNotEmpty(phone)) {
            booleanValidator.validateIfFalse(phoneValidator.validatePhone(phone), BAD_REQUEST,
                    invalidCustomerPhoneErrorMessage, PHONE);
        }
    }

    public void validateZipCode(String zipCode) {
        if (StringUtils.isNotEmpty(zipCode)) {
            booleanValidator.validateIfFalse(zipCodeValidator.validateZipCode(zipCode), BAD_REQUEST,
                    invalidCustomerZipCodeErrorMessage, ZIPCODE);
        }
    }

    public void validateDuplication(CustomerDto customerDto, List<User> users) {
        users.forEach(user -> {
            Customer customer = user.getCustomer();
            equalsValidator.validateIfEqualsIgnoreCase(customerDto.getName(), customer.getName(), ALREADY_EXISTS,
                    duplicateCustomerNameErrorMessage);
            equalsValidator.validateIfEqualsIgnoreCase(customerDto.getFirstName(), user.getFirstName(), ALREADY_EXISTS,
                    duplicateCustomerFirstNameErrorMessage);
            equalsValidator.validateIfEqualsIgnoreCase(customerDto.getEmail(), user.getUsername(), ALREADY_EXISTS,
                    duplicateCustomerEmailErrorMessage);
        });
    }
}
