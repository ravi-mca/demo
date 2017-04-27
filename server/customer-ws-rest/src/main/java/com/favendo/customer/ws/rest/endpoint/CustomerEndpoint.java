package com.favendo.customer.ws.rest.endpoint;

import com.favendo.customer.service.service.CustomerService;
import com.favendo.customer.ws.rest.builder.CustomerBuilder;
import com.favendo.customer.ws.rest.dto.CustomerDto;
import com.favendo.customer.ws.rest.validator.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static com.favendo.commons.utils.Routes.ADMIN;
import static com.favendo.commons.utils.Routes.CUSTOMER;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path(ADMIN + CUSTOMER)
@Produces(value = {APPLICATION_JSON})
@Consumes(value = {APPLICATION_JSON})
public class CustomerEndpoint {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerBuilder customerBuilder;

    @Autowired
    private CustomerValidator customerValidator;

    @POST
    public Response save(@RequestBody CustomerDto customerDto) {
        customerValidator.validateRequest(customerDto);
        customerService.save(customerBuilder.buildCustomer(customerDto), customerBuilder.buildCustomerUser(customerDto));
        return Response.status(Response.Status.CREATED).build();
    }
}
