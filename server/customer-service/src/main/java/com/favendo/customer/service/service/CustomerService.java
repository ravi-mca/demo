package com.favendo.customer.service.service;

import java.util.List;

import com.favendo.commons.domain.Customer;
import com.favendo.commons.domain.User;
import com.favendo.commons.exception.BusinessException;

public interface CustomerService {
    
    List<Customer> getAll() throws BusinessException;

    Customer getById(Long customerId);

    void save(Customer customer, User user);

    void delete(Long customerId);
}
