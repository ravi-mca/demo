package com.favendo.user.service.dao;

import com.favendo.user.service.domain.StorecastUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<StorecastUser,Long> {

    @Query("select storecastUser from StorecastUser storecastUser where storecastUser.username = ?1")
    StorecastUser findByUsername(String username);
}
