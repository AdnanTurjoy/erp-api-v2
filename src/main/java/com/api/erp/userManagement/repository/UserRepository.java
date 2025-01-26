package com.api.erp.userManagement.repository;

import com.api.erp.userManagement.entity.UserMgt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserMgt, Long> {
    List<UserMgt> findByRoleId(long roleId);

    UserMgt findByUsername(String username);
}
