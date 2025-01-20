package com.api.erp.procurementManagement.repository;

import com.api.erp.procurementManagement.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {}

