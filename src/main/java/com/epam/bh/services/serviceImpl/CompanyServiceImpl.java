package com.epam.bh.services.serviceImpl;


import com.epam.bh.dao.CompanyDAO;
import com.epam.bh.entities.Company;
import com.epam.bh.services.ServiceDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CompanyServiceImpl implements ServiceDAO<Company> {
    private static final Logger log = Logger.getLogger(CompanyServiceImpl.class.getName());

    private CompanyDAO companyDAO;

    public CompanyServiceImpl(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    @Override
    public Company add(Company company) {
        Company companyResult = this.companyDAO.save(company);
        log.info("Company " + companyResult.getName() + " was successfully added. Company details: " + companyResult.toString());
        return companyResult;
    }

    @Override
    public boolean update(Company company) {
        Optional<Company> companyById = companyDAO.findById(company.getId());
        if(!companyById.isPresent())
        {
            return false;
        }
        this.companyDAO.save(company);
        log.info("Company " + company.getName() + " was successfully updated. Company details: " + company.toString());
        return true;
    }

    @Override
    public void delete(long id) {
        Optional<Company> company = this.companyDAO.findById(id);
        log.info("All companies list size: " + this.companyDAO.findAll().size());
        this.companyDAO.deleteById(id);
        log.info("Company " + company.get().getName() + " was successfully deleted. After removing the size of the list of all companies: " + this.companyDAO.findAll().size());
    }

    @Override
    @Transactional
    public Company getById(long id) {
        Optional<Company> company = this.companyDAO.findById(id);
        log.info("Company with id [" + id + "] details: " + company.toString());
        return company.get();
    }

    @Override
    public List<Company> getAll() {
        List<Company> companyList = this.companyDAO.findAll();
        log.info("All companies list size: " + companyList.size());
        return companyList;
    }
}
