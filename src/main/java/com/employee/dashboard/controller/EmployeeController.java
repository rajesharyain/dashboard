package com.employee.dashboard.controller;

import com.employee.dashboard.model.Employee;
import com.employee.dashboard.service.EmployeeService;
import com.employee.dashboard.service.EmployeeServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService empService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> findAllEmployees() {
        List<Employee> employees = empService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> findAllEmployeeById(@PathVariable("id") Long id) {
        Optional<Employee> empOptional = empService.findEmployeeById(id);
        Employee employee  = empOptional.get();
        if(employee == null) {
            LOGGER.info("{} employee does not exists",id);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/employee")
    public void saveEmployee(@RequestBody Employee emp) {
        empService.saveEmployee(emp);

    }

    @DeleteMapping("/employee/{id}")
    public void saveEmployee(@PathVariable Long id) {
        empService.deleteEmployee(id);
        LOGGER.info("{} employee deleted successfully", id);
    }
}
