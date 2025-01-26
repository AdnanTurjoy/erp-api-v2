package com.api.erp.hrManagement.services.impl;

import com.api.erp.hrManagement.dtos.AttendanceDTO;
import com.api.erp.hrManagement.entity.Attendance;
import com.api.erp.hrManagement.entity.Employee;
import com.api.erp.hrManagement.repository.AttendanceRepository;
import com.api.erp.hrManagement.repository.EmployeeRepository;
import com.api.erp.hrManagement.services.AttendanceService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Override
    public Attendance markAttendance(AttendanceDTO attendanceDTO) {
        // Ensure the employee exists
        Employee existing = employeeRepository.findById(attendanceDTO.getEmployeeId()).orElseThrow(() -> new RuntimeException("Employee not found"));
        Attendance attendance = new Attendance();
        attendance.setEmployee(existing);
        attendance.setDate(attendanceDTO.getDate());
        attendance.setStatus(attendanceDTO.getStatus());
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
}
