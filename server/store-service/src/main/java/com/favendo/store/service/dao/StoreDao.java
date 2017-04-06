package com.favendo.store.service.dao;

import com.favendo.commons.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreDao extends JpaRepository<Store, Long> {

    @Query("select store from Store store where store.name = :name OR store.nickName = :nickName")
    Store findByNameOrNickName(@Param("name") String name, @Param("nickName") String nickName);

    @Query("select store from Store store where (store.name = :name OR store.nickName = :nickName) AND store.id <> :id")
    Store findByNameOrNickNameAndId(@Param("name") String username, @Param("nickName")
            String accountName, @Param("id") Long id);
}
