package com.api.erp.userManagement.repository;

import com.api.erp.userManagement.entity.RoleMgt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<RoleMgt, Long> {
}
