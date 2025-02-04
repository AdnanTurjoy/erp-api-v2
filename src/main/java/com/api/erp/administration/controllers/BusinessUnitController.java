package com.api.erp.administration.controllers;

import com.api.erp.administration.entity.BusinessUnit;
import com.api.erp.administration.services.BusinessUnitService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/business-units")
@AllArgsConstructor
public class BusinessUnitController {
    private final BusinessUnitService businessUnitService;

    @GetMapping
    public ResponseEntity<List<BusinessUnit>> getAllBusinessUnits() {
        return ResponseEntity.ok(businessUnitService.getAllBusinessUnits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessUnit> getBusinessUnitById(@PathVariable Long id) {
        Optional<BusinessUnit> businessUnit = businessUnitService.getBusinessUnitById(id);
        return businessUnit.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BusinessUnit> createBusinessUnit(@RequestBody BusinessUnit businessUnit) {
        return ResponseEntity.ok(businessUnitService.createBusinessUnit(businessUnit));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessUnit> updateBusinessUnit(@PathVariable Long id, @RequestBody BusinessUnit updatedBusinessUnit) {
        return ResponseEntity.ok(businessUnitService.updateBusinessUnit(id, updatedBusinessUnit));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusinessUnit(@PathVariable Long id) {
        businessUnitService.deleteBusinessUnit(id);
        return ResponseEntity.noContent().build();
    }
}
