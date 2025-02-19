package com.api.erp.hrManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supervisors")
public class Supervisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "department_code")
    private Department department;

    // One-to-One relationship with Employee
    @OneToOne(mappedBy = "supervisor")
    private Employee employee;
}
