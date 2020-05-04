package Reposiroty;

import dao.DAO;
import db.HibernateConnection;
import entity.Company;
import entity.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PersonRepository implements DAO<Person> {


    @Override
    public void add(Person person) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(person);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Person person) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(person);
        transaction.commit();
        session.close();
    }

    @Override
    public Person getById(int id) {
        return HibernateConnection.getSessionFactory().openSession().get(Person.class, id);
    }

    @Override
    public List<Person> getAll() {
        List<Person> personList = HibernateConnection.getSessionFactory().openSession().createQuery("FROM Person").list();
        return personList;
    }

    @Override
    public void delete(Person person) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        CompanyRepository companyRepository = new CompanyRepository();
        Transaction transaction = session.beginTransaction();
        Company company = person.getCompany();
        company.setBoss(null);
        companyRepository.update(company);
        transaction.commit();
        transaction = session.beginTransaction();
        session.delete(person);
        transaction.commit();
        session.close();
    }
}
