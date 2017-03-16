package com.favendo.portal.contextconfig;

import com.favendo.portal.security.SecurityConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SecurityConfiguration.class})
public class SecurityContextConfig {
}
