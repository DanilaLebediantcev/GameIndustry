package com.epam.bh.dao.daoImpl;

import com.epam.bh.dao.DAO;
import com.epam.bh.entities.Company;
import com.epam.bh.entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class PersonDAOImpl implements DAO<Person> {

    private EntityManagerFactory entityManagerFactory;

    public PersonDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void add(Person person) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(person);
            entityManager.getTransaction().commit();
        }
        catch (Exception exception) {
            entityManager.getTransaction().rollback();
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }

//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(person);
//        transaction.commit();
//        session.close();
    }

    @Override
    public void update(Person person) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(person);
            entityManager.getTransaction().commit();
        }
        catch (Exception exception) {
            entityManager.getTransaction().rollback();
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }

//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.update(person);
//        transaction.commit();
//        session.close();
    }

    @Override
    public Person getById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Person getPerson = null;
        try {
            getPerson = entityManager.createNamedQuery("Person.getById",Person.class).setParameter("id",id).getSingleResult();
            //getGame = entityManager.createQuery("select c from Company c where c.id =:id", Game.class).setParameter("id",id).getSingleResult();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return getPerson;
        //return HibernateConnection.getSessionFactory().openSession().get(Person.class, id);
    }

    @Override
    public List<Person> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Person> getAllPersons= null;
        try {
            getAllPersons = entityManager.createNamedQuery("Person.getAll", Person.class).getResultList();
            //getAllGames = entityManager.createQuery("from Company", Company.class).getResultList();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return getAllPersons;
//        List<Person> personList = HibernateConnection.getSessionFactory().openSession().createQuery("FROM Person").list();
//        return personList;
    }

    @Override
    public void deleteById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Person person = entityManager.createNamedQuery("Person.getById", Person.class).setParameter("id", id).getSingleResult();
            if (person.getCompany() != null) {
                Company company = person.getCompany();
                company.setBoss(null);
                entityManager.merge(company);
                entityManager.getTransaction().commit();
            }
            entityManager.getTransaction().begin();
            entityManager.remove(person);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }

//        Session session = HibernateConnection.getSessionFactory().openSession();
//        CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl();
//        Transaction transaction = session.beginTransaction();
//        if(person.getCompany()!=null) {
//            Company company = person.getCompany();
//            company.setBoss(null);
//            companyDAOImpl.update(company);
//            transaction.commit();
//            transaction = session.beginTransaction();
//        }
//        session.delete(person);
//        transaction.commit();
//        session.close();
//    }
    }
}
