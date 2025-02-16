package com.api.erp.hrManagement.services.impl;

import com.api.erp.hrManagement.dtos.ApplyLeaveDTO;
import com.api.erp.hrManagement.entity.Employee;
import com.api.erp.hrManagement.entity.Leave;
import com.api.erp.hrManagement.enums.LeaveStatus;
import com.api.erp.hrManagement.enums.LeaveType;
import com.api.erp.hrManagement.repository.LeaveRepository;
import com.api.erp.hrManagement.services.EmployeeService;
import com.api.erp.hrManagement.services.LeaveService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Leave applyLeave(ApplyLeaveDTO leave) {
        Employee employee = employeeService.getEmployeeById(leave.getEmployeeId());
        if(employee == null) {
            throw new RuntimeException("Employee Not Found");
        }
        log.info("Applying Leave===", employee);
        Leave newLeave = new Leave();
        newLeave.setEmployee(employee);
        newLeave.setLeaveType(LeaveType.valueOf(leave.getLeaveType()));
        newLeave.setAppliedAt(leave.getAppliedAt());
        newLeave.setStartDate(leave.getStartDate());
        newLeave.setEndDate(leave.getEndDate());
        newLeave.setStatus(LeaveStatus.PENDING);
        newLeave.setAppliedAt(leave.getAppliedAt());
        return leaveRepository.save(newLeave);

    }

    @Override
    public Leave updateLeaveStatus(Long leaveId, String status) {
        Leave leave = leaveRepository.findById(leaveId).orElseThrow(() -> new RuntimeException("Leave not found"));
        leave.setStatus(LeaveStatus.valueOf(status));
        return leaveRepository.save(leave);
    }

    @Override
    public List<Leave> getLeavesByEmployee(Long employeeId) {
        return leaveRepository.findAll().stream()
                .filter(l -> l.getEmployee().getId().equals(employeeId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }
}

