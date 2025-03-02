package com.api.erp.financeManagement.service;


import com.api.erp.financeManagement.dto.FinancialReportDTO;

import java.time.YearMonth;

public interface FinancialReportService {
    FinancialReportDTO generateFinancialReport(String companyCode, YearMonth month);
}

