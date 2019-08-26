package com.yaroslav.crm.dto.converter;

import com.yaroslav.crm.dto.CompanyDto;
import com.yaroslav.crm.dto.EmployeeDto;
import com.yaroslav.crm.model.Employee;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class EmployeeToEmployeeDtoConverter implements Converter<Employee, EmployeeDto> {
    @Override
    public EmployeeDto convert(MappingContext<Employee, EmployeeDto> context) {
        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(context.getSource().getId());
        employeeDto.setFirstName(context.getSource().getFirstName());
        employeeDto.setLastName(context.getSource().getLastName());
        employeeDto.setPhone(context.getSource().getPhone());
        employeeDto.setEmail(context.getSource().getEmail());

        if(context.getSource().getCompany() != null) {
            CompanyDto companyDto = new CompanyDto();
            companyDto.setId(context.getSource().getCompany().getId());
            companyDto.setName(context.getSource().getCompany().getName());

            employeeDto.setCompany(companyDto);
        }

        return employeeDto;
    }
}
