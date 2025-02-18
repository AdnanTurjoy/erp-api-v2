package com.api.erp.hrManagement.services.impl;

import com.api.erp.common.EmailService;
import com.api.erp.hrManagement.dtos.AttendanceDTO;
import com.api.erp.hrManagement.entity.Attendance;
import com.api.erp.hrManagement.entity.Employee;
import com.api.erp.hrManagement.repository.AttendanceRepository;
import com.api.erp.hrManagement.repository.EmployeeRepository;
import com.api.erp.hrManagement.services.AttendanceService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceServiceImpl.class);


    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmailService emailService;

    public String getStatusFollowedByCheckInTime (LocalDateTime checkInDateTime) {
        // Define the time thresholds for attendance status
        LocalTime presentStartTime = LocalTime.of(9, 0);
        LocalTime lateStartTime = LocalTime.of(9, 15);
        LocalTime absentStartTime = LocalTime.of(12, 0);

        // Extract the time from check-in
        if (checkInDateTime == null) {
            throw new IllegalArgumentException("Check-in time is required");
        }
        LocalTime checkInTime = checkInDateTime.toLocalTime();

        // Determine the status based on the check-in time
        String status;
        if (checkInTime.isBefore(lateStartTime)) {
            status = "Present";
        } else if (checkInTime.isBefore(absentStartTime)) {
            status = "Late";
        } else {
            status = "Absent";
        }
        return status;
    }

    @Override
    public Attendance markAttendance(AttendanceDTO attendanceDTO) {
        // Ensure the employee exists
        Employee existing = employeeRepository.findById(attendanceDTO.getEmployeeId()).orElseThrow(() -> new RuntimeException("Employee not found"));

        Attendance attendance = new Attendance();
        attendance.setEmployee(existing);
        attendance.setDate(attendanceDTO.getDate());
        attendance.setStatus(getStatusFollowedByCheckInTime(attendanceDTO.getCheckIn()));
        attendance.setCheckIn(attendanceDTO.getCheckIn());
       return attendanceRepository.save(attendance);

    }

    @Override
    public List<Attendance> getAttendanceByEmployee(Long employeeId) {
        return attendanceRepository.findAll().stream()
                .filter(a -> a.getEmployee().getId().equals(employeeId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    @Override
    public List<Attendance> findByEmployeeIdAndDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate) {
        return attendanceRepository.findByEmployeeIdAndDateBetween(employeeId, startDate, endDate);
    }

    @Override
    public List<Employee> findEmployeesLateOnDate(LocalDate date) {
        logger.info("INFO log message", date);
        return attendanceRepository.findEmployeesLateOnDate(date);
    }

    @Override
    public void notifyLateOrAbsentEmployees() throws MessagingException {
        LocalDate today = LocalDate.now();

        // Fetch all attendance records for "Late" or "Absent" where email is not sent
        List<Attendance> lateOrAbsentEmployees = attendanceRepository.findByDateAndStatusInAndEmailSentFalse(
                today, Arrays.asList("Late", "Absent"));

        // Send emails and mark them as notified
        for (Attendance attendance : lateOrAbsentEmployees) {
            String email = attendance.getEmployee().getEmail();
            String status = attendance.getStatus();
            emailService.sendEmail(email, "Attendance Notification",
                    "Dear " + attendance.getEmployee().getFirstName()+ " "+ attendance.getEmployee().getLastName()  + ",\n\n" +
                            "Your attendance status for today is: " + status + ". Please take necessary actions if needed.\n\n" +
                            "Best Regards,\nHR Team");

            // Mark the email as sent
            attendance.setEmailSent(true);
            attendanceRepository.save(attendance);  // Save updated email status
        }
    }

    @Override
    public List<Object> getBestPerformer(String filter) {
        return employeeRepository.findBestPerformer(filter);
    }

}
