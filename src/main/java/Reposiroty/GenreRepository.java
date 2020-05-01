package Reposiroty;

import dao.GenreDAO;
import db.HibernateConnection;
import entity.Game;
import entity.Genre;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GenreRepository implements GenreDAO {
    @Override
    public void addGenre(Genre genre) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(genre);
        transaction.commit();
        session.close();

    }

    @Override
    public void updateGenre(Genre genre) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(genre);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteGenre(Genre genre) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(genre);
        transaction.commit();
        session.close();
    }

    @Override
    public Genre getGenreById(int id) {
        return HibernateConnection.getSessionFactory().openSession().get(Genre.class,id);
    }

    @Override
    public List<Genre> getAllGenres() {
        List<Genre> genreList = HibernateConnection.getSessionFactory().openSession().createQuery("FROM Genre ").list();
        return  genreList;
    }
}
