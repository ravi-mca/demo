package com.favendo.store.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.favendo.commons.domain.Store;

@Repository
public interface StoreDao extends JpaRepository<Store, Long> {

    @Query("select store " +
            "from Store store " +
            "where upper(store.name) = upper(:name) OR " +
            "upper(store.nickName) = upper(:nickName) ")
    Store findByNameOrNickName(@Param("name") String name, @Param("nickName") String nickName);

    @Query("select store " +
            "from Store store " +
            "where (upper(store.name) = upper(:name) OR " +
            "upper(store.nickName) = upper(:nickName)) AND " +
            "store.id <> :id")
    Store findByNameOrNickNameAndId(@Param("name") String username, @Param("nickName")
            String accountName, @Param("id") Long id);
    
    @Query("select store from Store as store join store.merchant as merchant where merchant.merchantId = :merchantId ORDER BY store.name ASC")
    List<Store> findAllByMerchantId(@Param("merchantId") Long merchantId);
}
