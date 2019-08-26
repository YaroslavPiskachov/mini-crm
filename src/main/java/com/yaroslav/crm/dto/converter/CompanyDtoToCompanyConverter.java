package com.yaroslav.crm.dto.converter;

import com.yaroslav.crm.dto.CompanyDto;
import com.yaroslav.crm.dto.EmployeeDto;
import com.yaroslav.crm.model.Company;
import com.yaroslav.crm.model.Employee;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.HashSet;
import java.util.Set;

public class CompanyDtoToCompanyConverter implements Converter<CompanyDto, Company> {

    @Override
    public Company convert(MappingContext<CompanyDto, Company> context) {
        Company company = new Company();
        company.setId(context.getSource().getId());
        company.setName(context.getSource().getName());

        if(context.getSource().getEmployees() != null) {
            Set<Employee> employees = new HashSet<>();
            for (EmployeeDto employeeDto : context.getSource().getEmployees()) {
                employees.add(new Employee(employeeDto.getId(), employeeDto.getFirstName(), employeeDto.getLastName(),
                        employeeDto.getEmail(), employeeDto.getPhone(), company));
            }
            company.setEmployees(employees);
        }

        return company;
    }
}
