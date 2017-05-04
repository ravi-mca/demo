package com.favendo.merchant.service.contextconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"com.favendo.merchant.service.*"})
@EnableJpaRepositories(basePackages = {"com.favendo.merchant.service.dao"})
@EnableTransactionManagement
public class MerchantServiceContextConfig {

}
