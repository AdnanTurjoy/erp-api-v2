package com.api.erp.hrManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class PayrollDTO {
    private Long id;
    @NonNull
    private Long employeeId;

    @NonNull
    private String month; // YYYY-MM

    @NonNull
    private BigDecimal baseSalary;

    private BigDecimal bonuses;
    private BigDecimal deductions;

    @NonNull
    private BigDecimal netSalary;

    private LocalDateTime paidAt;
}
