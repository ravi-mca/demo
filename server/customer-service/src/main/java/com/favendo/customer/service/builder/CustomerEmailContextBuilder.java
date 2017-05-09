package com.favendo.customer.service.builder;

import com.favendo.commons.domain.Customer;
import com.favendo.commons.domain.User;
import com.favendo.commons.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomerEmailContextBuilder {

    @Autowired
    private PasswordGenerator passwordGenerator;

    public Map<String,Object> buildSaveCustomerEmailContext(Customer customer, User user){
        Map<String,Object> emailContext = new HashMap<>();
        emailContext.put("firstName",user.getFirstName());
        emailContext.put("lastName",user.getLastName());
        emailContext.put("emailAddress",user.getUsername());
        emailContext.put("password",passwordGenerator.getStrongRandomPassword());
        return emailContext;
    }
}
