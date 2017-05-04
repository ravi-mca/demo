package com.favendo.merchant.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.favendo.commons.domain.Merchant;

@Repository
public interface MerchantDao extends JpaRepository<Merchant, Long> {

    @Query("select merchant " +
            "from  Merchant merchant join fetch merchant.merchantUsers as merchantUsers order by merchant.accountName ASC ")
    List<Merchant> findAll();

    @Query("select merchant " +
            "from  Merchant merchant join fetch merchant.merchantUsers as merchantUsers " +
            "where merchant.merchantId = :merchantId ")
    Merchant findById(@Param("merchantId") Long merchantId);

    @Query("select merchant " +
            "from  Merchant merchant join fetch merchant.merchantUsers as merchantUsers " +
            "where merchant.accountNo = :accountNo ")
    Merchant findByAccountNo(@Param("accountNo") String accountNo);

    @Query("select merchant " +
            "from  Merchant merchant " +
            "where upper(merchant.accountName) = upper(:accountName) ")
    Merchant findByAccountName(@Param("accountName") String accountName);

    @Query("select merchant " +
            "from  Merchant merchant " +
            "where upper(merchant.accountName) = upper(:accountName) AND " +
            "merchant.merchantId <> :merchantId ")
    Merchant findByAccountNameAndMerchantId(@Param("accountName") String accountName,
                                            @Param("merchantId") Long merchantId);
}
