package com.favendo.customer.service.service;

import com.favendo.commons.domain.Customer;
import com.favendo.commons.domain.User;

public interface CustomerService {

    Customer getById(Long customerId);

    void save(Customer customer, User user);

    void delete(Long customerId);
}
