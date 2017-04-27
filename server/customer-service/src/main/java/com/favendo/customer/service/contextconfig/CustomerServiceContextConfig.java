package com.favendo.customer.service.contextconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.favendo.customer.service.*"})
public class CustomerServiceContextConfig {

}
