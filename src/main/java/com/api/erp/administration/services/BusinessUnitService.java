package com.api.erp.administration.services;

import com.api.erp.administration.entity.BusinessUnit;

import java.util.List;
import java.util.Optional;

public interface BusinessUnitService {
    List<BusinessUnit> getAllBusinessUnits();
    Optional<BusinessUnit> getBusinessUnitById(Long id);
    BusinessUnit createBusinessUnit(BusinessUnit businessUnit);
    BusinessUnit updateBusinessUnit(Long id, BusinessUnit updatedBusinessUnit);
    void deleteBusinessUnit(Long id);
}
