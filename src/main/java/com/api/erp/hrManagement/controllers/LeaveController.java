package com.api.erp.hrManagement.controllers;

import com.api.erp.common.EmailService;
import com.api.erp.hrManagement.dtos.ApplyLeaveDTO;
import com.api.erp.hrManagement.entity.Department;
import com.api.erp.hrManagement.entity.Leave;
import com.api.erp.hrManagement.services.DepartmentService;
import com.api.erp.hrManagement.services.LeaveService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private LeaveService leaveService;

    @PostMapping
    public Leave applyLeave(@RequestBody ApplyLeaveDTO leave) {
        return leaveService.applyLeave(leave);
    }

    @PutMapping("/{id}/status")
    public Leave updateLeaveStatus(@PathVariable Long id, @RequestParam String status) throws MessagingException {
        return leaveService.updateLeaveStatus(id, status);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Leave> getLeavesByEmployee(@PathVariable Long employeeId) {
        return leaveService.getLeavesByEmployee(employeeId);
    }

    @GetMapping
    public List<Leave> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

}
