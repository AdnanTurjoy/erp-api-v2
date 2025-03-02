package com.api.erp.financeManagement.controller;

import com.api.erp.financeManagement.dto.FinancialReportDTO;
import com.api.erp.financeManagement.service.FinancialReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class FinancialReportController {

    private final FinancialReportService reportService;

    @GetMapping("/{companyCode}/{year}/{month}")
    public ResponseEntity<FinancialReportDTO> getFinancialReport(@PathVariable String companyCode,
                                                                 @PathVariable int year,
                                                                 @PathVariable int month) {
        YearMonth reportMonth = YearMonth.of(year, month);
        return ResponseEntity.ok(reportService.generateFinancialReport(companyCode, reportMonth));
    }
}

