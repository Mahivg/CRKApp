package com.auidbook.prototype.Model.Fields;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mgundappan on 21-05-2016.
 */
public class Address {

    @JsonProperty("AddressType")
    public String addressType;

    @JsonProperty("AddressLine1")
    public String addressLine1;

    @JsonProperty("AddressLine2")
    public String addressLine2;

    @JsonProperty("AddressLine3")
    public String addressLine3;

    @JsonProperty("City")
    public String city;

    @JsonProperty("State")
    public String state;

    @JsonProperty("Country")
    public String country;

    @JsonProperty("ZipCode")
    public String zipCode;

    @JsonProperty("Latitude")
    public String latitude;

    @JsonProperty("Longitude")
    public String longitude;

    @JsonProperty("PrivacyStatus")
    public String privacyStatus;

    public Address() {
    }

    public Address(String addressType, String addressLine1, String addressLine2, String addressLine3, String city, String state, String country, String zipCode, String latitude, String longitude, String privacyStatus) {
        this.addressType = addressType;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.privacyStatus = privacyStatus;


    }

    public String getAddressType() {        return addressType;    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPrivacyStatus() {
        return privacyStatus;
    }

    public void setPrivacyStatus(String privacyStatus) {
        this.privacyStatus = privacyStatus;
    }

    @Override
    public String toString() {
        return getAddressLine3()+","+getCity();
    }
}
