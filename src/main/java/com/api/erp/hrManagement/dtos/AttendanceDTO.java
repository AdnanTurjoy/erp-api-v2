package com.api.erp.hrManagement.dtos;

import com.api.erp.hrManagement.entity.Employee;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {
    @NotNull
    private Long employeeId;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
//    private String status;
}
