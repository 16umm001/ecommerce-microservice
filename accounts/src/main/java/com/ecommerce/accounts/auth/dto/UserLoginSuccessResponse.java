package com.ecommerce.accounts.auth.dto;

import lombok.Data;

@Data
public class UserLoginSuccessResponse {
    private String token;
    private String type = "Bearer";

    public UserLoginSuccessResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
