package com.spring.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
public class EmployeeController {


    //@Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;


    @PostMapping("/employee")
    public Employee createEmployeeController(@Valid @RequestBody Employee employee) throws UserNotFoundExeption, SQLException {

        return employeeRepository.createEmployee(employee);
    }

    @GetMapping("/employee")

    public List<Employee> getallemployeeController(Employee employee) {
        return employeeRepository.getEmployees();

    }


    @GetMapping("/employee/{id}")
    public Employee getempbyidController(@PathVariable int id, Employee employee) throws UserNotFoundExeption {
        return employeeRepository.getEmpById(id, employee);
    }

    @PutMapping("/employee/{id}")
    public Employee updatingdata(@PathVariable int id, @RequestBody Employee employee) throws UserNotFoundExeption {
        return employeeRepository.updateEmployee(employee);
    }

    @DeleteMapping("/employee/{id}")
    public String deleteall(@PathVariable int id, Employee employee) {
        return employeeRepository.deleteEmployee(id, employee);
    }

    @PutMapping("/employee/put/{id}")
    public Employee updateResume(@PathVariable int id, @RequestBody Employee employee) throws UserNotFoundExeption {
        return employeeRepository.updateResume(id, employee);
    }

}
