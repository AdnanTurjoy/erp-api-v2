package com.api.erp.hrManagement.services;

import com.api.erp.hrManagement.entity.Leave;

import java.util.List;

public interface LeaveService {
    Leave applyLeave(Leave leave);
    Leave updateLeaveStatus(Long leaveId, String status);
    List<Leave> getLeavesByEmployee(Long employeeId);
    List<Leave> getAllLeaves();
}