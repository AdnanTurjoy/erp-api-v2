package com.api.erp.hrManagement.services.impl;

import com.api.erp.hrManagement.entity.Leave;
import com.api.erp.hrManagement.repository.LeaveRepository;
import com.api.erp.hrManagement.services.LeaveService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Override
    public Leave applyLeave(Leave leave) {
        return leaveRepository.save(leave);
    }

    @Override
    public Leave updateLeaveStatus(Long leaveId, String status) {
        Leave leave = leaveRepository.findById(leaveId).orElseThrow(() -> new RuntimeException("Leave not found"));
        leave.setStatus(status);
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

