package com.favendo.customer.ws.rest.contextconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.favendo.customer.ws.rest.*"})
public class CustomerRestContextConfig {
}
