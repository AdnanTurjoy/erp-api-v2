package com.api.erp.hrManagement.services.impl;

import com.api.erp.hrManagement.dtos.PayrollDTO;
import com.api.erp.hrManagement.entity.Employee;
import com.api.erp.hrManagement.entity.Payroll;
import com.api.erp.hrManagement.repository.EmployeeRepository;
import com.api.erp.hrManagement.repository.PayrollRepository;
import com.api.erp.hrManagement.services.PayrollService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PayrollServiceImpl implements PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Payroll generatePayroll(PayrollDTO payroll) {
        Payroll p = new Payroll();

        Employee employee = employeeRepository.findById(payroll.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Employee with ID " + payroll.getEmployeeId() + " not found"));

        // Populate the Payroll entity
        p.setEmployee(employee);
        p.setMonth(payroll.getMonth());
        p.setBaseSalary(payroll.getBaseSalary());
        p.setNetSalary(payroll.getNetSalary());
        p.setPaidAt(payroll.getPaidAt());
        p.setDeductions(payroll.getDeductions());
        p.setBonuses(payroll.getBonuses());

        // Save and return the Payroll entity
        return payrollRepository.save(p);
    }

    @Override
    public Payroll getPayrollByEmployeeAndMonth(Long employeeId, String month) {
        return payrollRepository.findAll().stream()
                .filter(p -> p.getEmployee().getId().equals(employeeId) && p.getMonth().equals(month))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Payroll not found"));
    }

    @Override
    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }
}

