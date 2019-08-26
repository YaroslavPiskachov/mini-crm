package com.yaroslav.crm.controller;

import com.yaroslav.crm.dto.EmployeeDto;
import com.yaroslav.crm.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/crm/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public EmployeeDto addCompany(@RequestBody EmployeeDto employee) {
        return employeeService.save(employee);
    }

    @PostMapping("/update/{id}")
    public EmployeeDto addUpdate(@RequestBody EmployeeDto employee, @PathVariable Long id) {
        return employeeService.update(id, employee);
    }

    @DeleteMapping("/delete/{id}")
    public EmployeeDto delete(@PathVariable Long id) {
        return employeeService.delete(id);
    }

    @GetMapping("/get/{id}")
    public EmployeeDto get(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @GetMapping("/all")
    public List<EmployeeDto> getAllCompanies() {
        return employeeService.getAll();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFound(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> anyException(Exception ex) {
        return new ResponseEntity<>("Some error during processing request", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
