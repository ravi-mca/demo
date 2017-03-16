package com.favendo.user.service.service;


import com.favendo.user.service.domain.StorecastUser;

public interface UserService {

    StorecastUser getUserByUsername(String username);
}
