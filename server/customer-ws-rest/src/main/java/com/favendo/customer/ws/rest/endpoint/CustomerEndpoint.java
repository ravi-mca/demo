package com.favendo.customer.ws.rest.endpoint;

import static com.favendo.commons.utils.Routes.ADMIN;
import static com.favendo.commons.utils.Routes.CUSTOMER;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.favendo.customer.ws.rest.dto.CustomerDto;

@Component
@Path(ADMIN + CUSTOMER)
@Produces(value = {APPLICATION_JSON})
@Consumes(value = {APPLICATION_JSON})
public class CustomerEndpoint {

    @POST
    public Response save(@RequestBody CustomerDto customerDto ) {
        return null;
    }
}
