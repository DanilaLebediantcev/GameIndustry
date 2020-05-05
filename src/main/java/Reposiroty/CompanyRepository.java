package Reposiroty;

import dao.DAO;
import db.HibernateConnection;
import entity.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CompanyRepository implements DAO<Company> {

    @Override
    public void add(Company company) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(company);
        transaction.commit();
        session.close();

    }

    @Override
    public void update(Company company) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(company);
        transaction.commit();
        session.close();

    }

    @Override
    public Company getById(Long id) {
        return HibernateConnection.getSessionFactory().openSession().get(Company.class, id);

    }

    @Override
    public List<Company> getAll() {
        List<Company> companyList = HibernateConnection.getSessionFactory().openSession().createQuery("FROM Company").list();
        return companyList;
    }

    @Override
    public void delete(Company company) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(company);
        transaction.commit();
        session.close();
    }

}
