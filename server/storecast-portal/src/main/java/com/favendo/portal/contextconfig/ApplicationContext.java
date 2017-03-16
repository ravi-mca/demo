package com.favendo.portal.contextconfig;

import com.favendo.user.service.contextconfig.UserServiceContextConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan({ "com.favendo.portal.*" })
@Import({SecurityContextConfig.class, UserServiceContextConfig.class, DataSourceContextConfig.class})
public class ApplicationContext extends WebMvcConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
