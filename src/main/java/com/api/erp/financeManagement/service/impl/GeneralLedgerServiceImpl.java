package com.api.erp.financeManagement.service.impl;

import com.api.erp.financeManagement.dto.GeneralLedgerDTO;
import com.api.erp.financeManagement.entity.GeneralLedger;
import com.api.erp.financeManagement.repository.GeneralLedgerRepository;
import com.api.erp.financeManagement.service.GeneralLedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralLedgerServiceImpl implements GeneralLedgerService {

    private final GeneralLedgerRepository repository;

    @Override
    public GeneralLedger createLedger(GeneralLedgerDTO dto) {
        GeneralLedger ledger = GeneralLedger.builder()
                .accountName(dto.getAccountName())
                .accountType(dto.getAccountType())
                .balance(dto.getBalance())
                .transactionDate(dto.getTransactionDate())
                .currency(dto.getCurrency())
                .companyCode(dto.getCompanyCode())
                .build();
        return repository.save(ledger);
    }

    @Override
    public List<GeneralLedger> getAllLedgers() {
        return repository.findAll();
    }

    @Override
    public GeneralLedger getLedgerById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Ledger Not Found"));
    }
}

