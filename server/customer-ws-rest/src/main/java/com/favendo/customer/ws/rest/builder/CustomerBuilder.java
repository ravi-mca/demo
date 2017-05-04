package com.favendo.customer.ws.rest.builder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.favendo.commons.domain.Customer;
import com.favendo.commons.domain.User;
import com.favendo.customer.ws.rest.dto.CustomerDto;

@Component
public class CustomerBuilder {

    @Value("${customer.admin.user.default.password}")
    private String customerAdminUserDefaultPassword;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Customer buildCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setStreet(customerDto.getStreet());
        customer.setCity(customerDto.getCity());
        customer.setState(customerDto.getState());
        customer.setCountry(customerDto.getCountry());
        customer.setZipcode(customerDto.getZipcode());
        return customer;
    }

    public User buildCustomerUser(CustomerDto customerDto) {
        User user = new User();
        user.setUsername(customerDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(customerAdminUserDefaultPassword));
        user.setFirstName(customerDto.getFirstName());
        user.setLastName(customerDto.getLastName());
        user.setPhone(customerDto.getPhone());
        return user;
    }
    
    public User buildCustomerUser(CustomerDto customerDto, User user) {
        if (StringUtils.isNotEmpty(customerDto.getFirstName())) {
            user.setFirstName(customerDto.getFirstName());
        }
        if (StringUtils.isNotEmpty(customerDto.getLastName())) {
            user.setLastName(customerDto.getLastName());
        }
        if (StringUtils.isNotEmpty(customerDto.getEmail())) {
            user.setUsername(customerDto.getEmail());
        }
        if (StringUtils.isNotEmpty(customerDto.getPhone())) {
            user.setPhone(customerDto.getPhone());
        }
        return user;
    }

    public Customer buildCustomer(CustomerDto customerDto, Customer customer) {
        if (StringUtils.isNotEmpty(customerDto.getName())) {
            customer.setName(customerDto.getName());
        }
        if (StringUtils.isNotEmpty(customerDto.getStreet())) {
            customer.setStreet(customerDto.getStreet());
        }
        if (StringUtils.isNotEmpty(customerDto.getCity())) {
            customer.setCity(customerDto.getCity());
        }
        if (StringUtils.isNotEmpty(customerDto.getState())) {
            customer.setState(customerDto.getState());
        }
        if (StringUtils.isNotEmpty(customerDto.getCountry())) {
            customer.setCountry(customerDto.getCountry());
        }
        if (StringUtils.isNotEmpty(customerDto.getZipcode())) {
            customer.setZipcode(customerDto.getZipcode());
        }
        return customer;
    }
}


