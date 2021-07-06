package com.epam.bh.dao.daoImpl;

import com.epam.bh.dao.DAO;
import com.epam.bh.entities.Company;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CompanyDAOImpl implements DAO<Company> {

    private EntityManagerFactory entityManagerFactory;

    public CompanyDAOImpl() {
    }

    public CompanyDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Transactional
    @Override
    public void add(Company company) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(company);
            entityManager.getTransaction().commit();
        }
        catch (Exception exception) {
            entityManager.getTransaction().rollback();
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }

    }

    @Transactional
    @Override
    public void update(Company company) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(company);
            entityManager.getTransaction().commit();
        }
        catch (Exception exception) {
            entityManager.getTransaction().rollback();
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }


//
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.update(company);
//        transaction.commit();
//        session.close();

    }

    @Transactional
    @Override
    public void deleteById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Company deleteCompany = entityManager.createQuery("select c from Company c where c.id =:id", Company.class).setParameter("id",id).getSingleResult();
            entityManager.remove(deleteCompany);
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
//        session.delete(company);
//        transaction.commit();
//        session.close();
    }


    @Transactional
    @Override
    public Company getById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Company getCompany = null;
        try {
            getCompany = entityManager.createQuery("select c from Company c where c.id =:id", Company.class).setParameter("id",id).getSingleResult();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return getCompany;
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Company getCompany = entityManager.createQuery("select c from Company c where c.id =:id",Company.class).setParameter("id",1).getSingleResult();
//        entityManager.close();
//        return getCompany;
    }

    @Transactional
    @Override
    public List<Company> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Company> getAllCompanies = null;
        try {
            getAllCompanies = entityManager.createQuery("from Company", Company.class).getResultList();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return getAllCompanies;

//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        List<Company> companyList = entityManager.createQuery("from Company",Company.class).getResultList();
//        entityManager.close();
//        return companyList;
    }
}
