package com.epam.bh.dao.daoImpl;

import com.epam.bh.dao.DAO;
import com.epam.bh.entities.Game;
import com.epam.bh.entities.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class GenreDAOImpl implements DAO<Genre> {
    private EntityManagerFactory entityManagerFactory;

    public GenreDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void add(Genre genre) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(genre);
            entityManager.getTransaction().commit();
        }
        catch (Exception exception) {
            entityManager.getTransaction().rollback();
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Genre genre) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(genre);
            entityManager.getTransaction().commit();
        }
        catch (Exception exception) {
            entityManager.getTransaction().rollback();
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Genre genre = entityManager.createNamedQuery("Game.getById",Genre.class).setParameter("id",id).getSingleResult();
            for (Game game : genre.getGameList()) {
                game.getGenreList().remove(genre);
                genre.getGameList().remove(game);
                entityManager.merge(game);
                entityManager.merge(genre);
            }
            entityManager.remove(genre);
            entityManager.getTransaction().commit();
        }
        catch (Exception exception) {
            entityManager.getTransaction().rollback();
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Genre getById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Genre getGenre = null;
        try {
            getGenre = entityManager.createNamedQuery("Genre.getById",Genre.class).setParameter("id",id).getSingleResult();

        }
        catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return getGenre;
    }

    @Override
    public List<Genre> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Genre> getAllGenres= null;
        try {
            getAllGenres = entityManager.createNamedQuery("Genre.getAll", Genre.class).getResultList();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return getAllGenres;
    }
}
