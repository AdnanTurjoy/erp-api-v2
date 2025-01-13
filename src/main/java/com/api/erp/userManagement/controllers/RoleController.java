package com.api.erp.userManagement.controllers;


import com.api.erp.userManagement.entity.RoleMgt;
import com.api.erp.userManagement.services.RoleService;
import com.api.erp.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/roleMgt")
    public ApiResponse<RoleMgt> addRoles(@RequestBody RoleMgt role){
        System.out.println(role.getName()+"Adnan");
        RoleMgt newRole = roleService.addRole(role);
        return new ApiResponse<>(true, "Role created successfully", newRole);
    }

    @GetMapping("/roleMgt/{id}")
    public ApiResponse<RoleMgt> getRoleById(@PathVariable("id") int id){
        RoleMgt role = roleService.getRole(id);
        return new ApiResponse<>(true, "Role found", role);
    }

    @GetMapping("/roleMgt")
    public ApiResponse<List<RoleMgt>> getAllRole(){
        List<RoleMgt> roles = roleService.getRoles();
        return new ApiResponse<>(true, "Role found", roles);
    }
}
