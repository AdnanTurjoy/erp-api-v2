package com.api.erp.financeManagement.repository;


import com.api.erp.financeManagement.entity.GeneralLedger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneralLedgerRepository extends JpaRepository<GeneralLedger, Long> {
    List<GeneralLedger> findByCompanyCode(String companyCode);
}

