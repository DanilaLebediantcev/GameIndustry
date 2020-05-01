package Reposiroty;

import dao.PersonDAO;
import db.HibernateConnection;
import entity.Company;
import entity.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PersonRepository implements PersonDAO {


    @Override
    public void addPerson(Person person) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(person);
        transaction.commit();
        session.close();
    }

    @Override
    public void updatePerson(Person person) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        session.update(person);
        transaction.commit();
        session.close();
    }

    @Override
    public Person getPersonById(Person person) {
        return HibernateConnection.getSessionFactory().openSession().get(Person.class,person.getId());
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> personList = HibernateConnection.getSessionFactory().openSession().createQuery("FROM Person").list();
        return personList;
    }

    @Override
    public void deletePerson(Person person) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(person);
        transaction.commit();
        session.close();
    }
}
