package com.favendo.portal.contextconfig;

import com.favendo.commons.contextconfig.CommonsContextConfig;
import com.favendo.user.service.contextconfig.UserServiceContextConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan({"com.favendo.portal.*"})
@PropertySource("classpath:application.properties")
@Import({SecurityContextConfig.class, UserServiceContextConfig.class, DataSourceContextConfig.class,
        CommonsContextConfig.class})
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
