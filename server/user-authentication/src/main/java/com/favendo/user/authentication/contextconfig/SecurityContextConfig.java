package com.favendo.user.authentication.contextconfig;

import com.favendo.user.authentication.security.SecurityConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SecurityConfiguration.class})
public class SecurityContextConfig {
}
