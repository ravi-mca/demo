package com.favendo.user.service.contextconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({ "com.favendo.user.service.*" })
@EnableJpaRepositories(basePackages = {"com.favendo.user.service.dao"})
@EnableTransactionManagement
public class UserServiceContextConfig {

}
