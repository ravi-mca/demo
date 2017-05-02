package com.favendo.customer.ws.rest.endpoint;

import static com.favendo.commons.exception.ErrorKey.SERVER_ERROR;
import static com.favendo.commons.utils.Routes.ADMIN;
import static com.favendo.commons.utils.Routes.CUSTOMER;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.favendo.commons.exception.BusinessException;
import com.favendo.commons.exception.StorecastApiException;
import com.favendo.customer.service.service.CustomerService;
import com.favendo.customer.ws.rest.builder.CustomerBuilder;
import com.favendo.customer.ws.rest.converter.CustomerDtoConverter;
import com.favendo.customer.ws.rest.dto.CustomerDto;
import com.favendo.customer.ws.rest.validator.CustomerValidator;
import com.favendo.user.service.service.UserService;

@Component
@Path(ADMIN + CUSTOMER)
@Produces(value = {APPLICATION_JSON})
@Consumes(value = {APPLICATION_JSON})
public class CustomerEndpoint {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerBuilder customerBuilder;

    @Autowired
    private CustomerValidator customerValidator;
    
    @Autowired
    private CustomerDtoConverter customerDtoConverter;
    
    @GET
    public Response getAll() {
        try {
            return Response.ok().entity(customerDtoConverter.convertCustomers(customerService.getAll())).build();
        } catch (BusinessException exception) {
            throw new StorecastApiException(SERVER_ERROR, exception.getMessage());
        }
    }

    @POST
    public Response save(@RequestBody CustomerDto customerDto) {
        customerValidator.validateRequest(customerDto);
        customerValidator.validateDuplication(customerDto, userService.getByUsernameOrFirstNameOrCustomerName(
                customerDto.getEmail(), customerDto.getFirstName(), customerDto.getName()));
        customerService.save(customerBuilder.buildCustomer(customerDto), customerBuilder.buildCustomerUser(customerDto));
        return Response.status(Response.Status.CREATED).build();
    }
}
