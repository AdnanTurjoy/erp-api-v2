package com.api.erp.administration.repository;

import com.api.erp.administration.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByParentIsNull(); // Get top-level menus
}

