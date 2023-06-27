package com.ecommerce.accounts.users.dto;

import com.ecommerce.accounts.users.models.AddressType;

public class AddressResponse {
    private long id;
    private String address_field1;
    private String address_field2;
    private String city;
    private String country;
    private String pinCode;
    private AddressType addressType;
    private boolean isDefaultAddress;

    public AddressResponse(long id, String address_field1, String address_field2, String city, String country, String pinCode, AddressType addressType, boolean isDefaultAddress) {
        this.id = id;
        this.address_field1 = address_field1;
        this.address_field2 = address_field2;
        this.city = city;
        this.country = country;
        this.pinCode = pinCode;
        this.addressType = addressType;
        this.isDefaultAddress = isDefaultAddress;
    }

    public AddressResponse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress_field1() {
        return address_field1;
    }

    public void setAddress_field1(String address_field1) {
        this.address_field1 = address_field1;
    }

    public String getAddress_field2() {
        return address_field2;
    }

    public void setAddress_field2(String address_field2) {
        this.address_field2 = address_field2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public boolean isDefaultAddress() {
        return isDefaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        isDefaultAddress = defaultAddress;
    }
}
