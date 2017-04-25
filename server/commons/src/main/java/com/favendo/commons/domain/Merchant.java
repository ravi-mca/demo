package com.favendo.commons.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "sc_merchant", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"account_name"})
})
public class Merchant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sc_merchant_id")
    private Long merchantId;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "account_name")
    private String accountName;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant", cascade = CascadeType.ALL)
    private List<Store> stores;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "merchant", cascade = CascadeType.ALL)
    private List<User> merchantUsers;


    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public List<User> getMerchantUsers() {
        return merchantUsers;
    }

    public void setMerchantUsers(List<User> merchantUsers) {
        this.merchantUsers = merchantUsers;
    }
}
