package com.favendo.customer.service.service;

import java.util.List;

import com.favendo.commons.domain.Customer;
import com.favendo.commons.domain.User;
import com.favendo.commons.exception.BusinessException;

public interface CustomerService {
    
    List<Customer> getAll() throws BusinessException;

    Customer getById(Long customerId);

    Customer getByName(String name);

    void save(Customer customer, User user);
    
    void update(Customer customer, User user);

    void delete(Long customerId);

    Customer getByNameAndCustomerId(String name,Long customerId);
}
