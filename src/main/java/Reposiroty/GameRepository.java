package Reposiroty;

import dao.DAO;
import db.HibernateConnection;
import entity.Game;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GameRepository implements DAO<Game> {
    @Override
    public void add(Game game) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(game);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Game game) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(game);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Game game) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(game);
        transaction.commit();
        session.close();
    }

    @Override
    public Game getById(int id) {
        return HibernateConnection.getSessionFactory().openSession().get(Game.class, id);
    }

    @Override
    public List<Game> getAll() {
        List<Game> gameList = HibernateConnection.getSessionFactory().openSession().createQuery("FROM Game").list();
        return gameList;
    }
}
