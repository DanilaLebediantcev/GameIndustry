package Reposiroty;

import dao.DAO;
import db.HibernateConnection;
import entity.Game;
import entity.Genre;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GenreRepository implements DAO<Genre> {
    @Override
    public void add(Genre genre) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(genre);
        transaction.commit();
        session.close();

    }

    @Override
    public void update(Genre genre) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(genre);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Genre genre) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        for (Game game : genre.getGameList()) {
            game.getGenreList().remove(genre);
            game.getGenreList();
            genre.getGameList().remove(game);
            genre.getGameList();
            session.update(genre);
            session.update(game);
        }
        transaction.commit();
        session.close();

        session = HibernateConnection.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(genre);
        transaction.commit();
        session.close();
    }

    @Override
    public Genre getById(int id) {
        return HibernateConnection.getSessionFactory().openSession().get(Genre.class, id);
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> genreList = HibernateConnection.getSessionFactory().openSession().createQuery("FROM Genre ").list();
        return genreList;
    }
}
