package com.favendo.customer.ws.rest.converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.favendo.commons.domain.Customer;
import com.favendo.commons.domain.User;
import com.favendo.customer.ws.rest.dto.CustomerDto;

@Component
public class CustomerDtoConverter {

    public List<CustomerDto> convertCustomers(List<Customer> customers) {
        return customers.stream().map(this::convertCustomer).collect(Collectors.toList());
    }

    public CustomerDto convertCustomer(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        Optional<User> optional = customer.getCustomerUsers().stream().findFirst();
        convertCustomerDetails(customer, customerDto);
        convertAdminDetails(optional.get(), customerDto);
        return customerDto;
    }

    private void convertCustomerDetails(Customer customer, CustomerDto customerDto) {
        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setName(customer.getName());
    }

    private void convertAdminDetails(User user, CustomerDto customerDto) {
        customerDto.setFirstName(user.getFirstName());
        customerDto.setLastName(user.getLastName());
        customerDto.setEmail(customerDto.getEmail());
        customerDto.setPhone(user.getPhone());
    }
}
