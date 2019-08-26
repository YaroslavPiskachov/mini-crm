package com.yaroslav.crm.dto;


import java.util.Set;

public class CompanyDto {

    private Long id;

    private String name;

    private Set<EmployeeDto> employees;

    public CompanyDto() {
    }

    public CompanyDto(String name, Set<EmployeeDto> employees) {
        this.name = name;
        this.employees = employees;
    }

    public CompanyDto(Long id, String name, Set<EmployeeDto> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeDto> employees) {
        this.employees = employees;
    }

}
