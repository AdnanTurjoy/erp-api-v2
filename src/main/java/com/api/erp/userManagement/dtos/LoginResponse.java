package com.api.erp.userManagement.dtos;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;

    private long expiresIn;

}
