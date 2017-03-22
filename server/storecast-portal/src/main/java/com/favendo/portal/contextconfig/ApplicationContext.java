package com.favendo.portal.contextconfig;

import com.favendo.commons.contextconfig.CommonsContextConfig;
import com.favendo.merchant.service.contextconfig.MerchantServiceContextConfig;
import com.favendo.merchant.ws.rest.contextconfig.MerchantRestContextConfig;
import com.favendo.user.service.contextconfig.UserServiceContextConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan({"com.favendo.portal.*"})
@Import({SecurityContextConfig.class, UserServiceContextConfig.class, DataSourceContextConfig.class,
        CommonsContextConfig.class, PropertySourceContextConfig.class, MerchantRestContextConfig.class,
        MerchantServiceContextConfig.class})
public class ApplicationContext extends WebMvcConfigurerAdapter {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
