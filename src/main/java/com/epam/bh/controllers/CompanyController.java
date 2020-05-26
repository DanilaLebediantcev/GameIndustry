package com.epam.bh.controllers;


import com.epam.bh.entities.Company;
import com.epam.bh.services.ServiceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/companies")
public class CompanyController {

    private final ServiceDAO<Company> companyServiceDAO;
    private Logger log = LoggerFactory.getLogger(CompanyController.class);

    public CompanyController(@Qualifier("companyServiceDAO") ServiceDAO<Company> companyServiceDAO) {
        this.companyServiceDAO = companyServiceDAO;
    }

    @GetMapping(path = "/getAll")
    public List<Company> getCompanies() {
        return companyServiceDAO.getAll();

    }

    @PostMapping(path = "/add")
    public void addCompany(@RequestBody Company company) {
        companyServiceDAO.add(company);
        log.info("----- !!!!!!  Added company from country controller: " + company.toString());
    }

    @GetMapping(path = "/getById/{id}")
    public Company findCompanyById(@PathVariable(name = "id") Long id) {
        return companyServiceDAO.getById(id);
    }

    @PostMapping(path = "/update")
    public void updateCompany(@RequestBody Company company) {
        companyServiceDAO.update(company);
        log.info("----- updated country from country controller: " + company);
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteCompany(@PathVariable(name = "id") Long id) {
        Company deleteCompany = companyServiceDAO.getById(id);
        companyServiceDAO.delete(id);
        return deleteCompany.toString() + ". This company was deleted. Pls, navigate to /companies/getAll";
    }
}