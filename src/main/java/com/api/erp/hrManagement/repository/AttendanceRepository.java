package com.api.erp.hrManagement.repository;

import com.api.erp.hrManagement.entity.Attendance;
import com.api.erp.hrManagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Custom query to find attendance records for an employee between two dates
    @Query("SELECT a FROM Attendance a WHERE a.employee.id = :employeeId AND a.date BETWEEN :startDate AND :endDate")
    List<Attendance> findByEmployeeIdAndDateBetween(@Param("employeeId") Long employeeId,
                                                    @Param("startDate") LocalDate startDate,
                                                    @Param("endDate") LocalDate endDate);

    // Custom query to find employees who were late on a specific date
    @Query("SELECT DISTINCT a.employee FROM Attendance a WHERE a.date = :date AND a.status = 'Late'")
    List<Employee> findEmployeesLateOnDate(@Param("date") LocalDate date);
}

