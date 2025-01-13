package com.api.erp.userManagement.services.impl;

import com.api.erp.userManagement.entity.RoleMgt;
import com.api.erp.userManagement.repository.RoleRepository;
import com.api.erp.userManagement.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp; // Corrected import
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleMgt getRole(long roleId) {
        return roleRepository.findById(roleId).orElse(null); // Use orElse to avoid NoSuchElementException
    }

    @Override
    public RoleMgt addRole(RoleMgt role) {
        System.out.println(role);
        if (role.getName() == null || role.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be empty");
        }

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        role.setCreatedAt(currentTime);
        role.setUpdatedAt(currentTime);
        return roleRepository.save(role);

    }

    @Override
    public List<RoleMgt> getRoles() {
        return roleRepository.findAll();
    }
}
