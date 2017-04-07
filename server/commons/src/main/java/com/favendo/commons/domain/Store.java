package com.favendo.commons.domain;

import javax.persistence.*;

@Entity
@Table(name = "sc_store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "storeId")
    private String storeId;

    @Column(name = "name")
    private String name;

    @Column(name = "nickName")
    private String nickName;

    @Column(name = "brandId")
    private String brandId;

    @Column(name = "managerOrPOC")
    private String managerOrPOC;

    @Column(name = "phone")
    private String phone;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "zipCode")
    private String zipCode;

    @Column(name = "controllerNumber")
    private String controllerNumber;

    @Column(name = "controllerPlacement")
    private String controllerPlacement;

    @Column(name = "wifiName")
    private String wifiName;

    @Column(name = "wifiPassword")
    private String wifiPassword;

    @Column(name = "category")
    private String category;

    @Column(name = "subCategory")
    private String subCategory;

    @Column(name = "priceCategory")
    private String priceCategory;

    @Column(name = "posSystem")
    private String posSystem;

    @Column(name = "loyaltyProgram")
    private String loyaltyProgram;

    @Column(name = "otherSystem")
    private String otherSystem;

    @Column(name = "storecastAdminName")
    private String storecastAdminName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id", nullable = false)
    private User merchant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getManagerOrPOC() {
        return managerOrPOC;
    }

    public void setManagerOrPOC(String managerOrPOC) {
        this.managerOrPOC = managerOrPOC;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getControllerNumber() {
        return controllerNumber;
    }

    public void setControllerNumber(String controllerNumber) {
        this.controllerNumber = controllerNumber;
    }

    public String getControllerPlacement() {
        return controllerPlacement;
    }

    public void setControllerPlacement(String controllerPlacement) {
        this.controllerPlacement = controllerPlacement;
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public String getWifiPassword() {
        return wifiPassword;
    }

    public void setWifiPassword(String wifiPassword) {
        this.wifiPassword = wifiPassword;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(String priceCategory) {
        this.priceCategory = priceCategory;
    }

    public String getPosSystem() {
        return posSystem;
    }

    public void setPosSystem(String posSystem) {
        this.posSystem = posSystem;
    }

    public String getLoyaltyProgram() {
        return loyaltyProgram;
    }

    public void setLoyaltyProgram(String loyaltyProgram) {
        this.loyaltyProgram = loyaltyProgram;
    }

    public String getOtherSystem() {
        return otherSystem;
    }

    public void setOtherSystem(String otherSystem) {
        this.otherSystem = otherSystem;
    }

    public String getStorecastAdminName() {
        return storecastAdminName;
    }

    public void setStorecastAdminName(String storecastAdminName) {
        this.storecastAdminName = storecastAdminName;
    }

    public User getMerchant() {
        return merchant;
    }

    public void setMerchant(User merchant) {
        this.merchant = merchant;
    }
}
