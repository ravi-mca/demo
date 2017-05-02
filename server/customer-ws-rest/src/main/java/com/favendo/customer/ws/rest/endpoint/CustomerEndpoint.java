package com.favendo.customer.ws.rest.endpoint;

import com.favendo.commons.domain.Customer;
import com.favendo.commons.domain.User;
import com.favendo.commons.exception.BusinessException;
import com.favendo.commons.exception.StorecastApiException;
import com.favendo.customer.service.service.CustomerService;
import com.favendo.customer.ws.rest.builder.CustomerBuilder;
import com.favendo.customer.ws.rest.converter.CustomerDtoConverter;
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
import java.util.Optional;

import static com.favendo.commons.exception.ErrorKey.BAD_REQUEST;
import static com.favendo.commons.exception.ErrorKey.SERVER_ERROR;
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
    
    @Autowired
    private CustomerDtoConverter customerDtoConverter;

    @Value("${customer.not.found.error.message}")
    private String customerNotFoundErrorMessage;
    
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
    
    @PUT
    @Path(PATH_PARAM_CUSTOMER_ID)
    public Response update(@RequestBody CustomerDto customerDto, @PathParam(CUSTOMER_ID) Long customerId) {
        Customer customer = getAndValidateCustomerById(customerId);
        Optional<User> optional = customer.getCustomerUsers().stream().findFirst();
        validateUpdateRequest(customerDto, customer);
        customerService.update(customerBuilder.buildCustomer(customerDto, customer),customerBuilder.buildCustomerUser(customerDto, optional.get()));
        return Response.status(Response.Status.OK).build();
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
    
    private void validateUpdateRequest(@RequestBody CustomerDto customerDto, Customer customer) {
        customerValidator.validateContactDetails(customerDto.getEmail(), customerDto.getPhone());
        customerValidator.validateDuplication(customerDto, userService.findByUsernameOrNameAndCustomerId(
                customerDto.getEmail(), customerDto.getName(), customer.getCustomerId()));
    }
}
