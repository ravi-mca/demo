package com.favendo.user.ws.rest.contextconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.favendo.user.ws.rest.*"})
public class UserRestContextConfig {
}
