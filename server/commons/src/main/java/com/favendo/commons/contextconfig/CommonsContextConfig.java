package com.favendo.commons.contextconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.favendo.commons.*"})
public class CommonsContextConfig {
}
