package Reposiroty;

import dao.CompanyDAO;
import db.HibernateConnection;
import entity.Company;
import entity.Game;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class CompanyRepository implements CompanyDAO {

    @Override
    public void addCompany(Company company) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(company);
        transaction.commit();
        session.close();

    }

    @Override
    public void updateCompany(Company company) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        session.update(company);
        transaction.commit();
        session.close();

    }

    @Override
    public Company getCompanyById(Company company) {
        return HibernateConnection.getSessionFactory().openSession().get(Company.class,company.getId());

    }

    @Override
    public List<Company> getAllCompany() {
        List<Company> companyList = HibernateConnection.getSessionFactory().openSession().createQuery("FROM Company").list();
        return companyList;
    }

    @Override
    public void deleteCompany(Company company) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(company);
        transaction.commit();
        session.close();
    }

//    @Override
//    public void addGameToGamesList(Game game) {
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Company company = game.getCompany();
//        company.getGamesList().add(game);
//        game.setCompany(company);
//        session.update(company);
//        transaction.commit();
//        session.close();
//    }
//
//    @Override
//    public void deleteGameFromGamesList(Game game) {
//
//    }
}
