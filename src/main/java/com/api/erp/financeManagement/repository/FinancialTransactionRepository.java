package com.api.erp.financeManagement.repository;

import com.api.erp.financeManagement.entity.FinancialTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;
import java.util.List;

public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction, Long> {
    List<FinancialTransaction> findByCompanyCodeAndTransactionDateBetween(String companyCode, java.time.LocalDate startDate, java.time.LocalDate endDate);
}

