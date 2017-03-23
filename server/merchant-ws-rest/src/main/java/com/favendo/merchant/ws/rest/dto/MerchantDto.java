package com.favendo.merchant.ws.rest.dto;

public class MerchantDto {

    private Long userId;
    private String firstname;
    private String lastname;
    private String accountNo;
    private String phone;
    private String username;

    public MerchantDto() {
        super();
    }

    public MerchantDto(Long userId, String firstname, String lastname, String accountNo, String phone, String username) {
        super();
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.accountNo = accountNo;
        this.phone = phone;
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "MerchantDto [userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname + ", accountNo="
                + accountNo + ", phone=" + phone + ", username=" + username + "]";
    }
}
