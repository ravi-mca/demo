package com.favendo.user.service.service;

import com.favendo.user.service.domain.Role;
import com.favendo.user.service.domain.StorecastUser;
import com.favendo.user.service.domain.StorecastUserDetails;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StorecastUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StorecastUser storecastUser = getAndValidateUser(username);
        return new StorecastUserDetails(storecastUser, getUserAuthorities(storecastUser.getRoles()));
    }

    private StorecastUser getAndValidateUser(String username) {
        StorecastUser storecastUser = userService.getUserByUsername(username);
        if (Objects.isNull(storecastUser)) {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }
        return storecastUser;
    }

    private List<GrantedAuthority> getUserAuthorities(List<Role> roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        roles.forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return grantedAuthorities;
    }
}