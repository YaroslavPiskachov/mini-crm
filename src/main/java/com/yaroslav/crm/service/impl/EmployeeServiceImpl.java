package com.yaroslav.crm.service.impl;

import com.yaroslav.crm.dto.EmployeeDto;
import com.yaroslav.crm.model.Employee;
import com.yaroslav.crm.repository.EmployeeRepository;
import com.yaroslav.crm.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final static String employeeNotFoundMsg = "Employee with id: %s not found";

    private final ModelMapper modelMapper;

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto save(EmployeeDto employee) {
        Employee savedEmployee = employeeRepository.save(modelMapper.map(employee, Employee.class));
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto getById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(employeeNotFoundMsg, id)));
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getAll() {
        List<EmployeeDto> result = new ArrayList<>();
        employeeRepository.findAll().forEach(c -> result.add(modelMapper.map(c, EmployeeDto.class)));
        return result;
    }

    @Override
    public EmployeeDto update(Long id, EmployeeDto employee) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(employeeNotFoundMsg, id)));
        Employee employeeToUpdate = modelMapper.map(employee, Employee.class);
        employeeToUpdate.setId(id);

        Employee savedEmployee= employeeRepository.save(employeeToUpdate);

        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto delete(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(employeeNotFoundMsg, id)));

        employeeRepository.delete(employee);

        return modelMapper.map(employee, EmployeeDto.class);
    }
}
