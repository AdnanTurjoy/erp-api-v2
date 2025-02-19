package com.api.erp.hrManagement.controllers;

import com.api.erp.hrManagement.entity.Supervisor;
import com.api.erp.hrManagement.services.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supervisors")
public class SupervisorController {
    @Autowired
    private SupervisorService supervisorService;

    // GET: Get all supervisors
    @GetMapping
    public List<Supervisor> getAllSupervisors() {
        return supervisorService.getAllSupervisors();
    }

    // POST: Create new supervisor
    @PostMapping
    public Supervisor createSupervisor(@RequestBody Supervisor supervisor) {
        return supervisorService.createSupervisor(supervisor);
    }
}
