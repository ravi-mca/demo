package com.favendo.portal.security;

import com.favendo.user.service.service.StorecastUserDetailsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

@Component
public class StorecastAuthenticationProvider implements AuthenticationProvider, MessageSourceAware {

    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private StorecastUserDetailsService storecastUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = getAndValidateUserDetails(username, password);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public void setMessageSource(final MessageSource messageSource) {
        messages = new MessageSourceAccessor(messageSource);
    }

    public MessageSourceAccessor getMessages() {
        return messages;
    }

    // Replace storecast with custom for class names
    private UserDetails getAndValidateUserDetails(String username, String password) {
        UserDetails userDetails = storecastUserDetailsService.loadUserByUsername(username);
        if (Objects.isNull(userDetails) || !StringUtils.endsWithIgnoreCase(userDetails.getUsername(), username)) {
            throw new BadCredentialsException("Invalid username or password");
        }
        if (!bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }
        return userDetails;
    }
}