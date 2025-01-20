package com.api.erp.procurementManagement.services;

import com.api.erp.procurementManagement.entity.Vendor;

import java.util.List;
import java.util.Optional;

public interface VendorService {
    Vendor saveVendor(Vendor vendor);
    Optional<Vendor> getVendorById(Long id);
    List<Vendor> getAllVendors();
    void deleteVendor(Long id);
}
