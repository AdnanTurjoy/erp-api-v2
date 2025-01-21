package com.api.erp.hrManagement.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "leaves")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String leaveType; // Sick Leave, Casual Leave, Paid Leave
    private LocalDate startDate;
    private LocalDate endDate;
    private String status; // Pending, Approved, Rejected
    private LocalDateTime appliedAt;

}

