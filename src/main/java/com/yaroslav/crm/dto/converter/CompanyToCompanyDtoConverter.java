package com.yaroslav.crm.dto.converter;


import com.yaroslav.crm.dto.CompanyDto;
import com.yaroslav.crm.dto.EmployeeDto;
import com.yaroslav.crm.model.Company;
import com.yaroslav.crm.model.Employee;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.HashSet;
import java.util.Set;

public class CompanyToCompanyDtoConverter implements Converter<Company, CompanyDto> {
    @Override
    public CompanyDto convert(MappingContext<Company, CompanyDto> context) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(context.getSource().getId());
        companyDto.setName(context.getSource().getName());

        if(context.getSource().getEmployees() != null) {
            Set<EmployeeDto> employees = new HashSet<>();
            for (Employee employee : context.getSource().getEmployees()) {
                employees.add(new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(),
                        employee.getEmail(), employee.getPhone()));
            }
            companyDto.setEmployees(employees);
        }

        return companyDto;
    }
}

