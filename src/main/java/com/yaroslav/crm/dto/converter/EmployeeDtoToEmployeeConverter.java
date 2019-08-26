package com.yaroslav.crm.dto.converter;

import com.yaroslav.crm.dto.EmployeeDto;
import com.yaroslav.crm.model.Company;
import com.yaroslav.crm.model.Employee;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class EmployeeDtoToEmployeeConverter implements Converter<EmployeeDto, Employee> {
    @Override
    public Employee convert(MappingContext<EmployeeDto, Employee> context) {
        Employee employee = new Employee();

        employee.setId(context.getSource().getId());
        employee.setFirstName(context.getSource().getFirstName());
        employee.setLastName(context.getSource().getLastName());
        employee.setPhone(context.getSource().getPhone());
        employee.setEmail(context.getSource().getEmail());

        if(context.getSource().getCompany() != null) {
            Company company = new Company();
            company.setId(context.getSource().getCompany().getId());
            company.setName(context.getSource().getCompany().getName());

            employee.setCompany(company);
        }

        return employee;
    }
}
