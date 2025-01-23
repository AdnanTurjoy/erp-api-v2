package com.api.erp.hrManagement.services.impl;

import com.api.erp.hrManagement.entity.Payroll;
import com.api.erp.hrManagement.repository.PayrollRepository;
import com.api.erp.hrManagement.services.PayrollService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PayrollServiceImpl implements PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    @Override
    public Payroll generatePayroll(Payroll payroll) {
        return payrollRepository.save(payroll);
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

