package com.codebuffer.departmentservice.controller;

import com.codebuffer.departmentservice.client.EmployeeClient;
import com.codebuffer.departmentservice.model.Department;
import com.codebuffer.departmentservice.repository.DepartmentRepository;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private EmployeeClient employeeClient;
    @PostMapping
    public Department add(@RequestBody Department department) {
        LOGGER.info("Department add: {}" + department);
        return repository.addDepartment(department);
    }

    @GetMapping("/{Id}")
    public Department findById(@PathVariable Long Id) {
        LOGGER.info("Department find by id={}" + Id);
        return repository.findById(Id);
    }

    @GetMapping
    public List<Department> findAll() {
        LOGGER.info("Department find all");
        return repository.findAll();
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        LOGGER.info("Department find with employees");
        List<Department> departments = repository.findAll();
        departments.forEach(department ->
                department.setEmployees(employeeClient.
                        findByDepartment(department.getId())));
        return departments;
    }

}
