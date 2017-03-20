package com.favendo.merchant.rest.contextconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.favendo.merchant.rest.*"})
public class MerchantContextConfig{
}
