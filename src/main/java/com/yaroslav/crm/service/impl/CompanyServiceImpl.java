package com.yaroslav.crm.service.impl;

import com.yaroslav.crm.dto.CompanyDto;
import com.yaroslav.crm.model.Company;
import com.yaroslav.crm.repository.CompanyRepository;
import com.yaroslav.crm.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final static String companyNotFoundMsg = "Company with id: %s not found";

    private final ModelMapper modelMapper;

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(ModelMapper modelMapper, CompanyRepository companyRepository) {
        this.modelMapper = modelMapper;
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyDto save(CompanyDto company) {
        Company savedCompany = companyRepository.save(modelMapper.map(company, Company.class));
        return modelMapper.map(savedCompany, CompanyDto.class);
    }

    @Override
    public CompanyDto getById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(companyNotFoundMsg, id)));
        return modelMapper.map(company, CompanyDto.class);
    }

    @Override
    public List<CompanyDto> getAll() {
        List<CompanyDto> result = new ArrayList<>();
        companyRepository.findAll().forEach(c -> result.add(modelMapper.map(c, CompanyDto.class)));
        return result;
    }

    @Override
    public CompanyDto update(Long id, CompanyDto company) {
        companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(companyNotFoundMsg, id)));
        Company companyToUpdate = modelMapper.map(company, Company.class);
        companyToUpdate.setId(id);

        Company savedCompany = companyRepository.save(companyToUpdate);
        return modelMapper.map(savedCompany, CompanyDto.class);
    }

    @Override
    public CompanyDto delete(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(companyNotFoundMsg, id)));

        companyRepository.delete(company);

        return modelMapper.map(company, CompanyDto.class);
    }
}
