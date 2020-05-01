package dao;

import entity.Company;

import java.util.List;

public interface CompanyDAO {
    public void addCompany(Company company);
    public void updateCompany(Company company);
    public Company getCompanyById(int id);
    public List<Company> getAllCompany();
    public void deleteCompany(Company company);

}
