package com.employee.dashboard.service;

import com.employee.dashboard.model.Employee;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAllEmployees();
    Optional<Employee> findEmployeeById(Long empId);
    //Optional<Employee> findEmployee(Employee emp);
    void saveEmployee(Employee emp);
    void deleteEmployee(Long id);

}
