package com.api.erp.hrManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String designation;
    private LocalDate hireDate;
    private BigDecimal salary;
    private String status; // Active, Inactive

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

