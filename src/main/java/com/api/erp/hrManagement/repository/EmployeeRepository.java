package com.api.erp.hrManagement.repository;

import com.api.erp.hrManagement.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findAll(Pageable pageable);

    @Query(value = """
        WITH monthly_attendance AS (
          SELECT 
            e.id AS employee_id,
            e.first_name,
            e.last_name,
            COUNT(DISTINCT a.date) AS days_present,
            SUM(CASE WHEN a.status = 'LEAVE' THEN 1 ELSE 0 END) AS leave_count,
            AVG(EXTRACT(EPOCH FROM (a.check_out - a.check_in))/3600) AS avg_hours_worked
          FROM 
            employees e
          LEFT JOIN 
            attendance a ON e.id = a.employee_id
          WHERE 
            CASE 
              WHEN LENGTH(:filter) = 4 THEN EXTRACT(YEAR FROM a.date) = CAST(:filter AS INT)
              WHEN LENGTH(:filter) = 2 THEN 
                EXTRACT(YEAR FROM a.date) = EXTRACT(YEAR FROM CURRENT_DATE)
                AND EXTRACT(MONTH FROM a.date) = CAST(:filter AS INT)
              ELSE FALSE
            END
          GROUP BY 
            e.id, e.first_name, e.last_name
        )
        SELECT 
          employee_id AS employeeId,
          first_name AS firstName,
          last_name AS lastName,
          days_present AS daysPresent,
          leave_count AS leaveCount,
          avg_hours_worked AS avgHoursWorked
        FROM 
          monthly_attendance
        WHERE 
          leave_count = (SELECT MIN(leave_count) FROM monthly_attendance)
        ORDER BY 
          avg_hours_worked DESC, days_present DESC
        """, nativeQuery = true)
    List<Object> findBestPerformer(@Param("filter") String filter);
}

