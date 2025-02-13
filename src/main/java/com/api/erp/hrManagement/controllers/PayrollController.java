package com.api.erp.hrManagement.controllers;
import com.api.erp.hrManagement.dtos.PayrollDTO;
import com.api.erp.hrManagement.entity.Department;
import com.api.erp.hrManagement.entity.Payroll;
import com.api.erp.hrManagement.services.DepartmentService;
import com.api.erp.hrManagement.services.PayrollService;
import com.api.erp.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/payroll")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @PostMapping
    public ApiResponse<Payroll> generatePayroll(@RequestBody PayrollDTO payroll) {
        Payroll p = payrollService.generatePayroll(payroll);
        return new ApiResponse<>(true, "Payroll created successfully", p);
    }

    @GetMapping("/employee/{employeeId}/month/{month}")
    public ApiResponse<Payroll> getPayrollByEmployeeAndMonth(@PathVariable Long employeeId, @PathVariable String month) {
        Payroll p = payrollService.getPayrollByEmployeeAndMonth(employeeId, month);
        return new ApiResponse<>(true, "Payroll found successfully", p);

    }

    @GetMapping
    public List<Payroll> getAllPayrolls() {
        return payrollService.getAllPayrolls();
    }
}

