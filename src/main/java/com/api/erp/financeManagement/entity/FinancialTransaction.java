package com.api.erp.financeManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountName;
    private String accountType;  // REVENUE, EXPENSE, ASSET, LIABILITY, EQUITY

    private BigDecimal transactionAmount;
    private LocalDate transactionDate;
    private String companyCode;
}

