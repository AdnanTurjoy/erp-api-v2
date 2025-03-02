package com.api.erp.financeManagement.controller;

import com.api.erp.financeManagement.dto.GeneralLedgerDTO;
import com.api.erp.financeManagement.entity.GeneralLedger;
import com.api.erp.financeManagement.service.GeneralLedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ledgers")
@RequiredArgsConstructor
public class GeneralLedgerController {

    private final GeneralLedgerService service;

    @PostMapping
    public ResponseEntity<GeneralLedger> createLedger(@RequestBody GeneralLedgerDTO dto) {
        return ResponseEntity.ok(service.createLedger(dto));
    }

    @GetMapping
    public ResponseEntity<List<GeneralLedger>> getAllLedgers() {
        return ResponseEntity.ok(service.getAllLedgers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralLedger> getLedgerById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getLedgerById(id));
    }
}

