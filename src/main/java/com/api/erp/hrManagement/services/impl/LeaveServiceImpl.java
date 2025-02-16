package com.api.erp.hrManagement.services.impl;

import com.api.erp.common.EmailService;
import com.api.erp.hrManagement.dtos.ApplyLeaveDTO;
import com.api.erp.hrManagement.entity.Employee;
import com.api.erp.hrManagement.entity.Leave;
import com.api.erp.hrManagement.enums.LeaveStatus;
import com.api.erp.hrManagement.enums.LeaveType;
import com.api.erp.hrManagement.repository.LeaveRepository;
import com.api.erp.hrManagement.services.EmployeeService;
import com.api.erp.hrManagement.services.LeaveService;
import jakarta.mail.MessagingException;
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

    @Autowired
    private EmailService emailService;

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
    public Leave updateLeaveStatus(Long leaveId, String status) throws MessagingException {
        Leave leave = leaveRepository.findById(leaveId).orElseThrow(() -> new RuntimeException("Leave not found"));
        if(leave != null) {
            String to = leave.getEmployee().getEmail();
            String leaveType = String.valueOf(leave.getLeaveType());
            String leaveStatus = status;

            String body = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Leave Status Update</title>\n" +
                    "    <style>\n" +
                    "        body {\n" +
                    "            font-family: Arial, sans-serif;\n" +
                    "            background-color: #f8f9fa;\n" +
                    "            padding: 20px;\n" +
                    "            margin: 0;\n" +
                    "        }\n" +
                    "        .email-container {\n" +
                    "            max-width: 600px;\n" +
                    "            margin: 0 auto;\n" +
                    "            background-color: #ffffff;\n" +
                    "            padding: 20px;\n" +
                    "            border: 1px solid #dee2e6;\n" +
                    "            border-radius: 8px;\n" +
                    "            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\n" +
                    "        }\n" +
                    "        h1 {\n" +
                    "            color: #343a40;\n" +
                    "            font-size: 24px;\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "        p {\n" +
                    "            color: #495057;\n" +
                    "            font-size: 16px;\n" +
                    "            line-height: 1.5;\n" +
                    "        }\n" +
                    "        .highlight {\n" +
                    "            font-weight: bold;\n" +
                    "            color: #007bff;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"email-container\">\n" +
                    "        <h1>Leave Status Update</h1>\n" +
                    "        <p>Hello,</p>\n" +
                    "        <p>Your leave request for <span class=\"highlight\">"+leaveType+"</span> has been updated to <span class=\"highlight\">"+leaveStatus+"</span>.</p>\n" +
                    "        <p>If you have any questions, please contact HR or your manager.</p>\n" +
                    "        <p>Best regards,</p>\n" +
                    "        <p>The HR Team</p>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>\n";
            emailService.sendEmail(to, "Your leave application is" + LeaveStatus.valueOf(status) , body);
            leave.setStatus(LeaveStatus.valueOf(status));
            return leaveRepository.save(leave);
        }
        return null;
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

