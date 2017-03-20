package com.favendo.user.service.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class StorecastUserDetails implements UserDetails {

    private StorecastUser storecastUser;

    private List<GrantedAuthority> authorities;

    public StorecastUserDetails(StorecastUser storecastUser, List<GrantedAuthority> authorities) {
        this.storecastUser = storecastUser;
        this.authorities = authorities;
    }

    public StorecastUser getStorecastUser() {
        return storecastUser;
    }

    public void setStorecastUser(StorecastUser storecastUser) {
        this.storecastUser = storecastUser;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return storecastUser.getPassword();
    }

    @Override
    public String getUsername() {
        return storecastUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}