package com.ecommerce.accounts.users.apis.v1.payload;

import com.ecommerce.accounts.users.models.AddressType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    @NotNull
    private String address_field1;

    private String address_field2;

    @NotNull
    private String city;

    @NotNull
    private String country;

    @NotNull
    private String pinCode;

    private AddressType addressType;

    private boolean isDefaultAddress;
}
