package com.favendo.customer.ws.rest.endpoint;

import com.favendo.commons.domain.Customer;
import com.favendo.commons.exception.StorecastApiException;
import com.favendo.customer.service.service.CustomerService;
import com.favendo.customer.ws.rest.builder.CustomerBuilder;
import com.favendo.customer.ws.rest.dto.CustomerDto;
import com.favendo.customer.ws.rest.validator.CustomerValidator;
import com.favendo.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Objects;

import static com.favendo.commons.exception.ErrorKey.BAD_REQUEST;
import static com.favendo.commons.utils.Routes.*;
import static com.favendo.customer.ws.rest.constant.CustomerConstant.CUSTOMER_ID;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

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

    @Value("${customer.not.found.error.message}")
    private String customerNotFoundErrorMessage;

    @POST
    public Response save(@RequestBody CustomerDto customerDto) {
        customerValidator.validateRequest(customerDto);
        customerValidator.validateDuplication(customerDto, userService.getByUsernameOrFirstNameOrCustomerName(
                customerDto.getEmail(), customerDto.getFirstName(), customerDto.getName()));
        customerService.save(customerBuilder.buildCustomer(customerDto), customerBuilder.buildCustomerUser(customerDto));
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path(PATH_PARAM_CUSTOMER_ID)
    public Response deleteById(@PathParam(CUSTOMER_ID) Long customerId) {
        getAndValidateCustomerById(customerId);
        customerService.delete(customerId);
        return Response.status(Response.Status.OK).build();
    }

    private Customer getAndValidateCustomerById(Long customerId) {
        Customer customer = customerService.getById(customerId);
        if (Objects.isNull(customer)) {
            throw new StorecastApiException(BAD_REQUEST, customerNotFoundErrorMessage, CUSTOMER_ID);
        }
        return customer;
    }
}
