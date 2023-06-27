package com.ecommerce.accounts.auth.api.v1.payload;

import jakarta.validation.constraints.NotNull;

public class UserRegisterRequest {
    @NotNull
    private String email;

    @NotNull
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
