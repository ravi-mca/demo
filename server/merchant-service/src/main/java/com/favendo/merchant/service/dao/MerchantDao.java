package com.favendo.merchant.service.dao;

import com.favendo.commons.domain.Merchant;
import com.favendo.commons.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantDao extends JpaRepository<Merchant, Long> {

    @Query("select merchant " +
            "from  Merchant merchant join fetch merchant.merchantUsers as merchantUsers " +
            "where merchant.merchantId = :merchantId ")
    Merchant findById(@Param("merchantId") Long merchantId);

    @Query("select merchant " +
            "from  Merchant merchant join fetch merchant.merchantUsers as merchantUsers " +
            "where merchant.accountNo = :accountNo ")
    Merchant findByAccountNo(@Param("accountNo") String accountNo);

    @Query("select merchant " +
            "from  Merchant merchant join fetch merchant.merchantUsers as merchantUsers ")
    List<Merchant> findAll();
}
