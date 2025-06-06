package com.api.erp.hrManagement.services;

import com.api.erp.hrManagement.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(Department department);
    Department updateDepartment(Long id, Department department);
    void deleteDepartment(Long id);
    Department getDepartmentById(Long id);
    List<Department> getAllDepartments();
}
