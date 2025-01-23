package com.api.erp.hrManagement.services;

import com.api.erp.hrManagement.entity.Attendance;

import java.util.List;

public interface AttendanceService {
    Attendance markAttendance(Attendance attendance);
    List<Attendance> getAttendanceByEmployee(Long employeeId);
    List<Attendance> getAllAttendance();
}
