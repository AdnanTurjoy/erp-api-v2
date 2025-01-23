package com.api.erp.hrManagement.controllers;
import com.api.erp.hrManagement.entity.Department;
import com.api.erp.hrManagement.entity.Payroll;
import com.api.erp.hrManagement.services.DepartmentService;
import com.api.erp.hrManagement.services.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/payroll")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @PostMapping
    public Payroll generatePayroll(@RequestBody Payroll payroll) {
        return payrollService.generatePayroll(payroll);
    }

    @GetMapping("/employee/{employeeId}/month/{month}")
    public Payroll getPayrollByEmployeeAndMonth(@PathVariable Long employeeId, @PathVariable String month) {
        return payrollService.getPayrollByEmployeeAndMonth(employeeId, month);
    }

    @GetMapping
    public List<Payroll> getAllPayrolls() {
        return payrollService.getAllPayrolls();
    }
}

