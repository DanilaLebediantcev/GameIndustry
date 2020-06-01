package com.epam.bh.controllers;


import com.epam.bh.entities.Company;
import com.epam.bh.services.ServiceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/companies")
public class CompanyController {

    private final ServiceDAO<Company> companyServiceDAO;
    private Logger log = LoggerFactory.getLogger(CompanyController.class);

    public CompanyController(ServiceDAO<Company> companyServiceDAO) {
        this.companyServiceDAO = companyServiceDAO;
    }

    @GetMapping(path = "/getAll")
    public List<Company> getCompanies() {
        return companyServiceDAO.getAll();

    }

    @PostMapping(path = "/add")
    public void addCompany(@RequestBody Company company) {
        companyServiceDAO.add(company);
        log.info("----- !!!!!!  Added company from company controller: " + company.toString());
    }

    @GetMapping(path = "/getById/{id}")
    public Company findCompanyById(@PathVariable(name = "id") Long id) {
       Company findByIdCompany = companyServiceDAO.getById(id);
       log.info("----- !!!!!! Company found: " + findByIdCompany.toString());
       return findByIdCompany;
    }

    @PutMapping(path = "/update")
    public void updateCompany(@RequestBody Company company) {
        companyServiceDAO.update(company);
        log.info("----- updated company from company controller: " + company);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteCompany(@PathVariable(name = "id") Long id) {
        companyServiceDAO.delete(id);
        log.info("----- Company with id ["+id +"] was deleted. Pls, navigate to /companies/getAll");
    }
}