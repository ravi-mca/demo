package com.favendo.portal.security;

import com.favendo.portal.filter.StorecastRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static com.favendo.commons.utils.Routes.LOGIN_REQUEST;
import static com.favendo.commons.utils.StringConstants.FORWARD_SLASH;
import static com.favendo.user.service.constant.StorecastUserConstant.PASSWORD;
import static com.favendo.user.service.constant.StorecastUserConstant.USERNAME;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private StorecastAuthenticationProvider storecastAuthenticationProvider;

    @Autowired
    private AuthenticationEntryPointHandler authenticationEntryPointHandler;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(storecastAuthenticationProvider);
    }

    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(FORWARD_SLASH).permitAll()
                .anyRequest().authenticated()
                .and()
                .authenticationProvider(storecastAuthenticationProvider)
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPointHandler)
                .and()
                .formLogin()
                .permitAll()
                .loginProcessingUrl(LOGIN_REQUEST)
                .usernameParameter(USERNAME)
                .passwordParameter(PASSWORD)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .logout()
                .permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore(new StorecastRequestFilter(), BasicAuthenticationFilter.class);
    }
}
