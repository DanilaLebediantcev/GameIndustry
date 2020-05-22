package com.epam.bh.dao.daoImpl;

import com.epam.bh.dao.DAO;
import com.epam.bh.entities.Game;
import com.epam.bh.entities.Genre;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class GameDAOImpl implements DAO<Game> {

    private EntityManagerFactory entityManagerFactory;

    public GameDAOImpl() {
    }

    public GameDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Transactional
    @Override
    public void add(Game game) {
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(game);
//        transaction.commit();
//        session.close();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(game);
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
    public void update(Game game) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(game);
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
//        session.update(game);
//        transaction.commit();
//        session.close();
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Game deleteGame = entityManager.createNamedQuery("Game.getById",Game.class).setParameter("id",id).getSingleResult();
            for (Genre genre : deleteGame.getGenreList()) {
                genre.getGameList().remove(deleteGame);
                deleteGame.getGenreList().remove(genre);
                entityManager.merge(deleteGame);
                entityManager.merge(genre);
            }
            entityManager.remove(deleteGame);
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
//        for (Genre genre : game.getGenreList()) {
//            genre.getGameList().remove(game);
//            game.getGenreList().remove(genre);
//            session.update(game);
//            session.update(genre);
//        }
//        session.delete(game);
//        transaction.commit();
//        session.close();
    }

    @Transactional
    @Override
    public Game getById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Game getGame = null;
        try {
            getGame = entityManager.createNamedQuery("Game.getById",Game.class).setParameter("id",id).getSingleResult();
            //getGame = entityManager.createQuery("select c from Company c where c.id =:id", Game.class).setParameter("id",id).getSingleResult();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return getGame;
    }

    @Transactional
    @Override
    public List<Game> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Game> getAllGames = null;
        try {
            getAllGames = entityManager.createNamedQuery("Game.getAll", Game.class).getResultList();
            //getAllGames = entityManager.createQuery("from Company", Company.class).getResultList();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return getAllGames;

//        List<Game> gameList = HibernateConnection.getSessionFactory().openSession().createQuery("FROM Game").list();
//        return gameList;
    }

//    @Override
//    public void addGameToCompanyGamesList(Game game) {
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Company company = game.getCompany();
//        company.addGameToGamesList(game);
//        session.update(company);
//        transaction.commit();
//        session.close();
//
//    }
}
