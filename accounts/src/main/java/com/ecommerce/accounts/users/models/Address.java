package com.ecommerce.accounts.users.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    private long id;

    @NotNull
    private String address_field1;

    private String address_field2;

    @NotNull
    private String city;

    @NotNull
    private String country;

    @NotNull
    private String pinCode;

    @Enumerated(EnumType.STRING)
    private AddressType addressType = AddressType.Home;

    private boolean isDefaultAddress = false;

    @ManyToOne
    @JoinColumn(name="users_profile_id")
    private UserProfile profile;

    public Address(long id, String address_field1, String address_field2, String city, String country, String pinCode, AddressType addressType, boolean isDefaultAddress) {
        this.id = id;
        this.address_field1 = address_field1;
        this.address_field2 = address_field2;
        this.city = city;
        this.country = country;
        this.pinCode = pinCode;
        this.addressType = addressType;
        this.isDefaultAddress = isDefaultAddress;
    }
}
