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
public class GeneralLedger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountName;
    private String accountType;  // ASSET, LIABILITY, EQUITY, REVENUE, EXPENSE
    private BigDecimal balance;
    private LocalDate transactionDate;

    private String currency;
    private String companyCode;
}

