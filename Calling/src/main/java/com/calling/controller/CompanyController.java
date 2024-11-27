package com.calling.controller;

import com.calling.entities.Calling;
import com.calling.entities.Company;
import com.calling.repository.CompanyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyRepository.save(company));
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return companyRepository.findById(id).map(x -> ResponseEntity.ok(x)).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Company> updateCompany(@RequestBody @Valid Company company) {
        return companyRepository.findById(company.getId()).map(x -> ResponseEntity.status(HttpStatus.CREATED).body(companyRepository.save(company))).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Long id) {
        Optional<Company> company = companyRepository.findById(id);

        if (company.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        companyRepository.deleteById(id);
    }

}
