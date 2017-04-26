package com.favendo.portal.contextconfig;

import com.favendo.user.authentication.contextconfig.UserAuthenticationContextConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan({"com.favendo.*"})
@Import({DataSourceContextConfig.class, PropertySourceContextConfig.class, UserAuthenticationContextConfig.class})
public class ApplicationContext extends WebMvcConfigurerAdapter {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
