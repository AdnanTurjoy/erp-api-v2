package com.api.erp.financeManagement.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneralLedgerDTO {
    private String accountName;
    private String accountType;
    private BigDecimal balance;
    private LocalDate transactionDate;
    private String currency;
    private String companyCode;
}
