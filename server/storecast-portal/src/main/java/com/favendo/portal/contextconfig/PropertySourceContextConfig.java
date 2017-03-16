package com.favendo.portal.contextconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:common/message/messages_en.properties")
public class PropertySourceContextConfig {
}
