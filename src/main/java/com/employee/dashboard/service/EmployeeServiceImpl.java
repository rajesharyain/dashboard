package com.employee.dashboard.service;

import com.employee.dashboard.model.Employee;
import com.employee.dashboard.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    public static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }
    @Override
    public Optional<Employee> findEmployeeById(Long empId) {
        return employeeRepository.findById(empId);
    }

    @Override
    public void saveEmployee(Employee emp) {

        if(emp.getId()!= null) {
            Optional<Employee> employee = findEmployeeById(emp.getId());
            if(employee.isPresent()) {
                //proceed with the update
                Employee toSaveEmployee;

                toSaveEmployee = employee.get();
                toSaveEmployee.setAge(emp.getAge());
                toSaveEmployee.setEmail(emp.getEmail());
                toSaveEmployee.setJoiningDate(emp.getJoiningDate());
                toSaveEmployee.setName(emp.getName());
                toSaveEmployee.setManagerId(emp.getManagerId());
                toSaveEmployee.setSalary(emp.getSalary());
                System.out.println(toSaveEmployee);
                employeeRepository.save(toSaveEmployee);
                LOGGER.info("employee {} updated successfully", toSaveEmployee);
            }else{
                LOGGER.info("employee {} does not exists!", emp);
            }
        }else {
            employeeRepository.save(emp);
            LOGGER.info("employee {} saved successfully", emp);
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        Optional<Employee> employee = findEmployeeById(id);
        if(employee.isPresent()) {
            employeeRepository.delete(employee.get());
            LOGGER.info("employee with id {} deleted!", id);
        }else{
            LOGGER.info("{} employee does not exists!", id);
        }
    }
   /*
    @Override
    public Optional<Employee> findEmployee(Employee emp) {
        return Optional.empty();
    }


    */
}
