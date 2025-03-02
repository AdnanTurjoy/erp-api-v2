package com.api.erp.financeManagement.service.impl;

import com.api.erp.financeManagement.dto.FinancialReportDTO;
import com.api.erp.financeManagement.entity.Budget;
import com.api.erp.financeManagement.entity.FinancialTransaction;
import com.api.erp.financeManagement.repository.BudgetRepository;
import com.api.erp.financeManagement.repository.FinancialTransactionRepository;
import com.api.erp.financeManagement.service.FinancialReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FinancialReportServiceImpl implements FinancialReportService {

    private final FinancialTransactionRepository transactionRepository;
    private final BudgetRepository budgetRepository;

    @Override
    public FinancialReportDTO generateFinancialReport(String companyCode, YearMonth month) {
        LocalDate startDate = month.atDay(1);
        LocalDate endDate = month.atEndOfMonth();

        // Fetch actual transactions
        List<FinancialTransaction> transactions = transactionRepository.findByCompanyCodeAndTransactionDateBetween(companyCode, startDate, endDate);

        // Fetch budgeted amounts
        List<Budget> budgets = budgetRepository.findByCompanyCodeAndMonth(companyCode, month);

        // Calculate totals
        BigDecimal totalRevenue = BigDecimal.ZERO;
        BigDecimal totalExpenses = BigDecimal.ZERO;
        BigDecimal totalAssets = BigDecimal.ZERO;
        BigDecimal totalLiabilities = BigDecimal.ZERO;
        BigDecimal totalEquity = BigDecimal.ZERO;

        for (FinancialTransaction txn : transactions) {
            switch (txn.getAccountType()) {
                case "REVENUE" -> totalRevenue = totalRevenue.add(txn.getTransactionAmount());
                case "EXPENSE" -> totalExpenses = totalExpenses.add(txn.getTransactionAmount());
                case "ASSET" -> totalAssets = totalAssets.add(txn.getTransactionAmount());
                case "LIABILITY" -> totalLiabilities = totalLiabilities.add(txn.getTransactionAmount());
                case "EQUITY" -> totalEquity = totalEquity.add(txn.getTransactionAmount());
            }
        }

        BigDecimal netProfit = totalRevenue.subtract(totalExpenses);

        // Compare Actual vs Budgeted
        Map<String, BigDecimal> actualVsBudget = new HashMap<>();
        for (Budget budget : budgets) {
            BigDecimal actualAmount = transactions.stream()
                    .filter(txn -> txn.getAccountName().equals(budget.getAccountName()))
                    .map(FinancialTransaction::getTransactionAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            actualVsBudget.put(budget.getAccountName(), actualAmount.subtract(budget.getBudgetedAmount()));
        }

        return FinancialReportDTO.builder()
                .totalRevenue(totalRevenue)
                .totalExpenses(totalExpenses)
                .netProfit(netProfit)
                .totalAssets(totalAssets)
                .totalLiabilities(totalLiabilities)
                .totalEquity(totalEquity)
                .actualVsBudget(actualVsBudget)
                .build();
    }
}

