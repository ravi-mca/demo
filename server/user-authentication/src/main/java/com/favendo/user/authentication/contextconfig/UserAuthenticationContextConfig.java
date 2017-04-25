package com.favendo.user.authentication.contextconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"com.favendo.user.authentication.*"})
@Import({SecurityContextConfig.class})
public class UserAuthenticationContextConfig {
}
