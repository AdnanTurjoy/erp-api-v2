package com.api.erp.hrManagement.services;

import com.api.erp.hrManagement.entity.Supervisor;

import java.util.List;

public interface SupervisorService {
    Supervisor createSupervisor(Supervisor supervisor);
    List<Supervisor> getAllSupervisors();
}
