package com.epam.bh.services.serviceImpl;


import com.epam.bh.dao.DAO;
import com.epam.bh.entities.Company;
import com.epam.bh.services.ServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service("CompanyServiceImpl")
public class CompanyServiceImpl implements ServiceDAO<Company> {
    private static final Logger log = Logger.getLogger(CompanyServiceImpl.class.getName());

    private DAO<Company> companyDAO;

    public CompanyServiceImpl(DAO<Company> companyDAO) {
        this.companyDAO = companyDAO;
    }

    @Override
    public void add(Company company) {
        this.companyDAO.add(company);
        log.info("Company " + company.getName() + "was successfully added. Company details: " + company.toString());
    }

    @Override
    public void update(Company company) {
        this.companyDAO.update(company);
        log.info("Company " + company.getName() + "was successfully updated. Company details: " + company.toString());
    }

    @Override
    public void delete(long id) {
        Company company = this.companyDAO.getById(id);
        log.info("All companies list size: " + this.companyDAO.getAll().size());
        this.companyDAO.deleteById(id);
        log.info("Company " + company.getName() + "was successfully deleted. After removing the size of the list of all companies: " + this.companyDAO.getAll().size());
    }

    @Override
    public Company getById(long id) {
        Company company = this.companyDAO.getById(id);
        log.info("Company with id [" + id + "] details: " + company.toString());
        return company;
    }

    @Override
    public List<Company> getAll() {
        List<Company> companyList = this.companyDAO.getAll();
        log.info("All companies list size: " + companyList.size());
        return this.companyDAO.getAll();
    }
}
