package com.yaroslav.crm.controller;

import com.yaroslav.crm.dto.CompanyDto;
import com.yaroslav.crm.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/crm/company")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/add")
    public CompanyDto addCompany(@RequestBody CompanyDto company) {
        return companyService.save(company);
    }

    @PostMapping("/update/{id}")
    public CompanyDto addUpdate(@RequestBody CompanyDto company, @PathVariable Long id) {
        return companyService.update(id, company);
    }

    @DeleteMapping("/delete/{id}")
    public CompanyDto delete(@PathVariable Long id) {
        return companyService.delete(id);
    }

    @GetMapping("/get/{id}")
    public CompanyDto get(@PathVariable Long id) {
        return companyService.getById(id);
    }

    @GetMapping("/all")
    public List<CompanyDto> getAllCompanies() {
        return companyService.getAll();
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
