package com.api.erp.hrManagement.services.impl;

import com.api.erp.hrManagement.entity.Supervisor;
import com.api.erp.hrManagement.repository.SupervisorRepository;
import com.api.erp.hrManagement.services.SupervisorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupervisorServiceImpl implements SupervisorService {

    private final SupervisorRepository supervisorRepository;

    @Override
    public Supervisor createSupervisor(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    @Override
    public List<Supervisor> getAllSupervisors() {
        return supervisorRepository.findAll();
    }
}
