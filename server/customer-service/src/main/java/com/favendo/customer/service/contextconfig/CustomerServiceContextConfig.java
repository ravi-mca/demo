package com.favendo.customer.service.contextconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"com.favendo.customer.service.*"})
@EnableJpaRepositories(basePackages = {"com.favendo.customer.service.dao"})
@EnableTransactionManagement
public class CustomerServiceContextConfig {
}
