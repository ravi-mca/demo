package com.favendo.user.service.service;

import com.favendo.commons.domain.Role;
import com.favendo.commons.domain.User;
import com.favendo.user.service.domain.StorecastUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Value("${invalid.user.credential.error.message}")
    private String invalidUserCredentialErrorMessage;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getAndValidateUser(username);
        return new StorecastUserDetails(user, getUserAuthorities(user.getRoles()));
    }

    private User getAndValidateUser(String username) {
        User user = userService.getByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(invalidUserCredentialErrorMessage);
        }
        return user;
    }

    private List<GrantedAuthority> getUserAuthorities(List<Role> roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        roles.forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return grantedAuthorities;
    }
}