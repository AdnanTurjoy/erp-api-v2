package com.api.erp.procurementManagement.services.impl;

import com.api.erp.procurementManagement.entity.Vendor;
import com.api.erp.procurementManagement.repository.VendorRepository;
import com.api.erp.procurementManagement.services.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    @Override
    public Vendor saveVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public Optional<Vendor> getVendorById(Long id) {
        return vendorRepository.findById(id);
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }
}

