package com.api.erp.hrManagement.services;

import com.api.erp.hrManagement.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    Page<Employee> getEmployeesWithPagination(int page, int size);

}

