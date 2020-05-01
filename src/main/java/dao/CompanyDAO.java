package dao;

import entity.Company;
import entity.Game;

import java.util.List;

public interface CompanyDAO {
    public void addCompany(Company company);
    public void updateCompany(Company company);
    public Company getCompanyById(Company company);
    public List<Company> getAllCompany();
    public void deleteCompany(Company company);
//    public void addGameToGamesList(Game game);
//    public void deleteGameFromGamesList(Game game);

}
