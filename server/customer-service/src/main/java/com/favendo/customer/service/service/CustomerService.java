package com.favendo.customer.service.service;

import java.util.List;

import com.favendo.commons.domain.Customer;
import com.favendo.commons.domain.User;
import com.favendo.commons.exception.BusinessException;

public interface CustomerService {
    
    List<Customer> getAll() throws BusinessException;

    void save(Customer customer, User user);
}
