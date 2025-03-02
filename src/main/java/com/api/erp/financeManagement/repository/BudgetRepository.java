package com.api.erp.financeManagement.repository;

import com.api.erp.financeManagement.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;
import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByCompanyCodeAndMonth(String companyCode, YearMonth month);
}
