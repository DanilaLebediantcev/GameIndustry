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
    }

    @Transactional
    @Override
    public Game getById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Game getGame = null;
        try {
            getGame = entityManager.createNamedQuery("Game.getById",Game.class).setParameter("id",id).getSingleResult();
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
        }
        catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return getAllGames;
    }
}
