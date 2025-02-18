package com.api.erp.hrManagement.services;

import com.api.erp.hrManagement.dtos.AttendanceDTO;
import com.api.erp.hrManagement.entity.Attendance;
import com.api.erp.hrManagement.entity.Employee;
import jakarta.mail.MessagingException;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    Attendance markAttendance(AttendanceDTO attendance);
    List<Attendance> getAttendanceByEmployee(Long employeeId);
    List<Attendance> getAllAttendance();
    List<Attendance> findByEmployeeIdAndDateBetween(Long employeeId, LocalDate  startDate, LocalDate endDate);

    List<Employee> findEmployeesLateOnDate(LocalDate date);

    void notifyLateOrAbsentEmployees() throws MessagingException;

    List<Object> getBestPerformer(String filter);
}
