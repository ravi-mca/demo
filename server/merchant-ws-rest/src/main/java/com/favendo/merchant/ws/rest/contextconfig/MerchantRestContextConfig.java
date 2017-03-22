package com.favendo.merchant.ws.rest.contextconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.favendo.merchant.ws.rest.*"})
public class MerchantRestContextConfig {
}
