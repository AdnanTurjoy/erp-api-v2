package com.api.erp.hrManagement.entity;

import com.api.erp.administration.entity.BusinessUnit;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")  // Prevents recursion
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
    @JoinColumn(name = "business_unit_id") // Foreign key column in employees table
    private BusinessUnit businessUnit;

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

