package com.favendo.store.ws.rest.contextconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.favendo.store.ws.rest.*"})
public class StoreRestContextConfig {
}
