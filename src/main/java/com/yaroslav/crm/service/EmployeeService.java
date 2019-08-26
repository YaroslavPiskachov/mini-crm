package com.yaroslav.crm.service;

import com.yaroslav.crm.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto save(EmployeeDto employee);

    EmployeeDto getById(Long id);

    List<EmployeeDto> getAll();

    EmployeeDto update(Long id, EmployeeDto employee);

    EmployeeDto delete(Long id);


}
