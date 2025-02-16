package com.api.erp.hrManagement.services;

import com.api.erp.hrManagement.dtos.ApplyLeaveDTO;
import com.api.erp.hrManagement.entity.Leave;
import jakarta.mail.MessagingException;

import java.util.List;

public interface LeaveService {
    Leave applyLeave(ApplyLeaveDTO leave);
    Leave updateLeaveStatus(Long leaveId, String status) throws MessagingException;
    List<Leave> getLeavesByEmployee(Long employeeId);
    List<Leave> getAllLeaves();
}