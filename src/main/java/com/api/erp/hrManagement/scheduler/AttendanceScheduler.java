package com.api.erp.hrManagement.scheduler;

import com.api.erp.hrManagement.services.AttendanceService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AttendanceScheduler {

    @Autowired
    private AttendanceService attendanceService;

//    @Scheduled(cron = "0 0 * * * ?")  // Run at the top of every hour
//    @Scheduled(cron = "0 30 12 * * ?")  // Cron expression for 12:30 PM daily
    @Scheduled(cron = "0 * * * * ?")  // This will run every minute for TEST
    public void sendLateOrAbsentEmails() throws MessagingException {
        System.out.println("Starting the scheduler to send emails for Late/Absent employees...");
        // Fetch today's attendance and filter out "Late" or "Absent"
        attendanceService.notifyLateOrAbsentEmployees();
    }
}
