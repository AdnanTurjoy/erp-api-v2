package com.api.erp.userManagement.dtos;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String email;
    private String password;
}
