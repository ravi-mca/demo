package com.favendo.store.ws.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("storeId")
    private String storeId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("nickName")
    private String nickName;

    @JsonProperty("brandId")
    private String brandId;

    @JsonProperty("managerOrPOC")
    private String managerOrPOC;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("street")
    private String street;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;

    @JsonProperty("country")
    private String country;

    @JsonProperty("zipCode")
    private String zipCode;

    @JsonProperty("controllerNumber")
    private String controllerNumber;

    @JsonProperty("controllerPlacement")
    private String controllerPlacement;

    @JsonProperty("wifiName")
    private String wifiName;

    @JsonProperty("wifiPassword")
    private String wifiPassword;

    @JsonProperty("category")
    private String category;

    @JsonProperty("subCategory")
    private String subCategory;

    @JsonProperty("priceCategory")
    private String priceCategory;

    @JsonProperty("posSystem")
    private String posSystem;

    @JsonProperty("loyaltyProgram")
    private String loyaltyProgram;

    @JsonProperty("otherSystem")
    private String otherSystem;

    @JsonProperty("storecastAdminName")
    private String storecastAdminName;

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
}
