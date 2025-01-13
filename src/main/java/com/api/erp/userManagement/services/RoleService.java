package com.api.erp.userManagement.services;

import com.api.erp.userManagement.entity.RoleMgt;

import java.util.List;

public interface RoleService {
    RoleMgt getRole(long roleId);
    RoleMgt addRole(RoleMgt role);

    List<RoleMgt> getRoles();
}
