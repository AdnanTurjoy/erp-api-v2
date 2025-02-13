package com.api.erp.hrManagement.services;

import com.api.erp.hrManagement.dtos.PayrollDTO;
import com.api.erp.hrManagement.entity.Payroll;

import java.util.List;

public interface PayrollService {
    Payroll generatePayroll(PayrollDTO payroll);
    Payroll getPayrollByEmployeeAndMonth(Long employeeId, String month);
    List<Payroll> getAllPayrolls();
}

