package com.favendo.portal.contextconfig;

import com.favendo.commons.contextconfig.CommonsContextConfig;
import com.favendo.user.service.contextconfig.UserServiceContextConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan({"com.favendo.portal.*"})
@Import({SecurityContextConfig.class, UserServiceContextConfig.class, DataSourceContextConfig.class,
        CommonsContextConfig.class,PropertySourceContextConfig.class})
public class ApplicationContext extends WebMvcConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
