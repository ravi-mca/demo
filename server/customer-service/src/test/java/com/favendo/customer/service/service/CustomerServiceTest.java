package com.favendo.customer.service.service;

import static  org.junit.Assert.assertEquals;
import static  org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.favendo.commons.domain.Customer;
import com.favendo.commons.domain.User;
import com.favendo.customer.service.dao.CustomerDao;
import com.favendo.customer.service.service.impl.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerDao customerDao;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    private static final int CUSTOMER_SIZE = 2;
    private static final int CUSTOMER_USERS_SIZE = 1;
    private static final String CUSTOMER1_FIRSTNAME = "Christian";
    private static final String CUSTOMER1_NAME = "favendo";
    private static final String CUSTOMER1_LASNAME = "Motz";
    private static final String CUSTOMER1_USERNAME = "test-admin1@favendo.com";
    private static final Long CUSTOMER1_USERID = 1l;
    private static final Long CUSTOMER1_ID = 1l;
    private static final String CUSTOMER1_PHONE = "12345676";
    private static final String CUSTOMER2_FIRSTNAME = "zymr";
    private static final String CUSTOMER2_NAME = "Raseel";
    private static final String CUSTOMER2_LASNAME = "Bhagat";
    private static final String CUSTOMER2_USERNAME = "test-admin2@zymr.com";
    private static final Long CUSTOMER2_ID = 2l;
    private static final Long CUSTOMER2_USERID = 2l;
    private static final String CUSTOMER2_PHONE = "98765432";

    @Test
    public void getAllCustomersWithCustomerUsers() {
        List<Customer> merchantList = buildCustomerList();
        Mockito.when(customerDao.findAll()).thenReturn(merchantList);
        List<Customer> customers = customerServiceImpl.getAll();
        assertNotNull(customers);
        assertEquals(CUSTOMER_SIZE, customers.size());
        assertEquals(CUSTOMER1_ID, customers.stream().findFirst().get().getCustomerId());
        assertEquals(CUSTOMER_USERS_SIZE, customers.stream().findFirst().get().getCustomerUsers().size());
        
    }

    private List<Customer> buildCustomerList() {
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = buildFirstCustomer();
        Customer customer2 = buildSecondCustomer();
        customers.add(customer1);
        customers.add(customer2);
        return customers;
    }

    private Customer buildFirstCustomer() {
        Customer customer = new Customer();
        customer.setName(CUSTOMER1_NAME);
        customer.setCustomerId(CUSTOMER1_ID);
        customer.setCustomerUsers(buildFirstCustomerUsers());
        return customer;
    }

    private Customer buildSecondCustomer() {
        Customer customer = new Customer();
        customer.setName(CUSTOMER2_NAME);
        customer.setCustomerId(CUSTOMER2_ID);
        customer.setCustomerUsers(buildSecondCustomerUsers());
        return customer;
    }
    
    private List<User> buildFirstCustomerUsers() {
        List<User> customerUsers = new ArrayList<>();
        customerUsers.add(buildFirstCustomerUser());
        return customerUsers;
    }

    private List<User> buildSecondCustomerUsers() {
        List<User> customerUsers = new ArrayList<>();
        customerUsers.add(buildSecondCustomerUser());
        return customerUsers;
    }

    private User buildFirstCustomerUser() {
        User user = new User();
        user.setUser_id(CUSTOMER1_USERID);
        user.setFirstName(CUSTOMER1_FIRSTNAME);
        user.setLastName(CUSTOMER1_LASNAME);
        user.setUsername(CUSTOMER1_USERNAME);
        user.setPhone(CUSTOMER1_PHONE);
        return user;
    }

    private User buildSecondCustomerUser() {
        User user = new User();
        user.setUser_id(CUSTOMER2_USERID);
        user.setFirstName(CUSTOMER2_FIRSTNAME);
        user.setLastName(CUSTOMER2_LASNAME);
        user.setUsername(CUSTOMER2_USERNAME);
        user.setPhone(CUSTOMER2_PHONE);
        return user;
    }
}
