package com.api.erp.financeManagement.service;


import com.api.erp.financeManagement.dto.GeneralLedgerDTO;
import com.api.erp.financeManagement.entity.GeneralLedger;

import java.util.List;

public interface GeneralLedgerService {
    GeneralLedger createLedger(GeneralLedgerDTO dto);
    List<GeneralLedger> getAllLedgers();
    GeneralLedger getLedgerById(Long id);
}

