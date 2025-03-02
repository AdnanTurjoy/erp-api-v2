package com.api.erp.financeManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.YearMonth;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountName;
    private String accountType;  // REVENUE, EXPENSE, ASSET, LIABILITY, EQUITY

    private BigDecimal budgetedAmount;
    private YearMonth month;
    private String companyCode;
}

