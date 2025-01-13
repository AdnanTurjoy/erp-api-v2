package com.api.erp.userManagement.repository;

import com.api.erp.userManagement.entity.UserMgt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserMgt, Long> {
}
