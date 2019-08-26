package com.yaroslav.crm.service;

import com.yaroslav.crm.dto.CompanyDto;

import java.util.List;

public interface CompanyService {

    CompanyDto save(CompanyDto company);

    CompanyDto getById(Long id);

    List<CompanyDto> getAll();

    CompanyDto update(Long id, CompanyDto company);

    CompanyDto delete(Long id);

}
