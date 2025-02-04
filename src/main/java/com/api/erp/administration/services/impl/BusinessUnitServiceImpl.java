package com.api.erp.administration.services.impl;

import com.api.erp.administration.repository.BusinessUnitRepository;
import com.api.erp.administration.services.BusinessUnitService;
import com.api.erp.administration.entity.BusinessUnit;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BusinessUnitServiceImpl implements BusinessUnitService {

    @Autowired
    private BusinessUnitRepository businessUnitRepository;

    @Override
    public List<BusinessUnit> getAllBusinessUnits() {
        return businessUnitRepository.findAll();
    }

    @Override
    public Optional<BusinessUnit> getBusinessUnitById(Long id) {
        return businessUnitRepository.findById(id);
    }

    @Override
    public BusinessUnit createBusinessUnit(BusinessUnit businessUnit) {
        return businessUnitRepository.save(businessUnit);
    }

    @Override
    public BusinessUnit updateBusinessUnit(Long id, BusinessUnit updatedBusinessUnit) {
        return businessUnitRepository.findById(id).map(existingUnit -> {
            existingUnit.setName(updatedBusinessUnit.getName());
            existingUnit.setDescription(updatedBusinessUnit.getDescription());
            return businessUnitRepository.save(existingUnit);
        }).orElseThrow(() -> new RuntimeException("Business Unit not found"));
    }

    @Override
    public void deleteBusinessUnit(Long id) {
        businessUnitRepository.deleteById(id);
    }
}
