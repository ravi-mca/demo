package com.favendo.customer.ws.rest.builder;

import com.favendo.commons.domain.Customer;
import com.favendo.commons.domain.User;
import com.favendo.customer.ws.rest.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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
}


