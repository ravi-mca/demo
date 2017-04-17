package com.favendo.portal.security;

import com.favendo.portal.filter.AuthenticationFilter;
import com.favendo.user.service.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static com.favendo.commons.utils.Routes.*;
import static com.favendo.commons.utils.StringConstants.FORWARD_SLASH;
import static com.favendo.user.service.constant.RoleConstant.HAS_ADMIN_ROLE;
import static com.favendo.user.service.constant.RoleConstant.HAS_MERCHANT_ROLE;
import static com.favendo.user.service.constant.UserConstant.PASSWORD;
import static com.favendo.user.service.constant.UserConstant.USERNAME;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Order(2)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private AuthenticationEntryPointHandler authenticationEntryPointHandler;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

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
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, ALL_REQUEST).permitAll()
                .antMatchers(ADMIN_REQUEST).access(HAS_ADMIN_ROLE)
                .antMatchers(MERCHANT_REQUEST).access(HAS_MERCHANT_ROLE)
                .and()
                .authorizeRequests().antMatchers(FORWARD_SLASH).permitAll()
                .anyRequest().authenticated()
                .and()
                .authenticationProvider(authenticationProvider).userDetailsService(userDetailsService)
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPointHandler)
                .accessDeniedHandler(accessDeniedHandler)
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
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(new AuthenticationFilter(userDetailsService), BasicAuthenticationFilter.class);
    }
}
