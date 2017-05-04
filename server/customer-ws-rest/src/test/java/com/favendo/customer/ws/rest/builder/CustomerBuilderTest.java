package com.favendo.customer.ws.rest.builder;

import com.favendo.commons.domain.Customer;
import com.favendo.commons.domain.User;
import com.favendo.customer.ws.rest.dto.CustomerDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testng.MockitoTestNGListener;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@Listeners(MockitoTestNGListener.class)
public class CustomerBuilderTest {

    @InjectMocks
    private CustomerBuilder subject;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String CUSTOMER_NAME = "StorecastUser";
    private static final String STREET = "SGVP";
    private static final String CITY = "Ahmedabad";
    private static final String STATE = "Gujarat";
    private static final String COUNTRY = "India";
    private static final String ZIPCODE = "392405";
    private static final String FIRSTNAME = "Vishavnath";
    private static final String LASNAME = "Punnapa";
    private static final String USERNAME = "vp@zymr.com";
    private static final String PASSWORD = "Customer@123";
    private static final String PHONE = "9898102525";

    @Test
    public void buildCustomer_WithAllDetails_ReturnsCustomer(){
        CustomerDto customerDto = buildCustomerDto(CUSTOMER_NAME,FIRSTNAME,LASNAME,USERNAME,PHONE,STREET,CITY,STATE,
                COUNTRY,ZIPCODE);

        Customer result = subject.buildCustomer(customerDto);
        Customer expected = buildCustomer(customerDto);

        assertThat(result.getName(), is(expected.getName()));
        assertThat(result.getStreet(), is(expected.getStreet()));
        assertThat(result.getCity(), is(expected.getCity()));
        assertThat(result.getState(), is(expected.getState()));
        assertThat(result.getCountry(), is(expected.getCountry()));
        assertThat(result.getZipcode(), is(expected.getZipcode()));
    }

    @Test
    public void buildCustomerUser_WithAllDetails_ReturnsCustomerUser(){
        CustomerDto customerDto = buildCustomerDto(CUSTOMER_NAME,FIRSTNAME,LASNAME,USERNAME,PHONE,STREET,CITY,STATE,
                COUNTRY,ZIPCODE);

        when(bCryptPasswordEncoder.encode(PASSWORD)).thenReturn(PASSWORD);

        User result = subject.buildCustomerUser(customerDto);
        User expected = subject.buildCustomerUser(customerDto);

        assertThat(result.getUsername(), is(expected.getUsername()));
        assertThat(result.getFirstName(), is(expected.getFirstName()));
        assertThat(result.getLastName(), is(expected.getLastName()));
        assertThat(result.getPhone(), is(expected.getPhone()));
    }

    private CustomerDto buildCustomerDto(String name,String firstName,String lastName,String username,String phone,
                                         String street,String city,String state,String country,String zipcode){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(name);
        customerDto.setFirstName(firstName);
        customerDto.setLastName(lastName);
        customerDto.setEmail(username);
        customerDto.setPhone(phone);
        customerDto.setStreet(street);
        customerDto.setCity(city);
        customerDto.setState(state);
        customerDto.setCountry(country);
        customerDto.setZipcode(zipcode);
        return customerDto;
    }

    private Customer buildCustomer(CustomerDto customerDto){
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
        user.setFirstName(customerDto.getFirstName());
        user.setLastName(customerDto.getLastName());
        user.setPhone(customerDto.getPhone());
        return user;
    }
}
