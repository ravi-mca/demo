package com.favendo.store.service.contextconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"com.favendo.store.service.*"})
@EnableJpaRepositories(basePackages = {"com.favendo.store.service.dao"})
@EnableTransactionManagement
public class StoreServiceContextConfig {
}
