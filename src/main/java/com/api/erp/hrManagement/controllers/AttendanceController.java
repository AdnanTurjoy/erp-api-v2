package com.api.erp.hrManagement.controllers;

import com.api.erp.hrManagement.dtos.AttendanceDTO;
import com.api.erp.hrManagement.entity.Attendance;
import com.api.erp.hrManagement.entity.Employee;
import com.api.erp.hrManagement.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping
    public Attendance markAttendance(@RequestBody AttendanceDTO attendance) {
        return attendanceService.markAttendance(attendance);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Attendance> getAttendanceByEmployee(@PathVariable Long employeeId) {
        return attendanceService.getAttendanceByEmployee(employeeId);
    }

    @GetMapping
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    // More Featured API

    @GetMapping("/employee/{employeeId}/between")
    public ResponseEntity<List<Attendance>> getAttendanceByEmployeeBetweenDates(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<Attendance> attendances = attendanceService.findByEmployeeIdAndDateBetween(employeeId, startDate, endDate);
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/late/{date}")
    public ResponseEntity<List<Employee>> getEmployeesLateOnDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<Employee> lateEmployees = attendanceService.findEmployeesLateOnDate(date);
        return ResponseEntity.ok(lateEmployees);
    }
}
