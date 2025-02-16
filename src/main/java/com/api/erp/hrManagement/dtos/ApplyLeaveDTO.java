package com.api.erp.hrManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ApplyLeaveDTO {

    @NonNull
    private Long employeeId;

    @NonNull
    private String leaveType;

    @NonNull
    private LocalDate startDate;
    private LocalDate endDate;
    private String status; // Pending, Approved, Rejected
    private LocalDateTime appliedAt;
}
