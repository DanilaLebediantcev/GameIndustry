package Reposiroty;

import dao.GameDAO;
import db.HibernateConnection;
import entity.Company;
import entity.Game;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GameRepository implements GameDAO {
    @Override
    public void addGame(Game game) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(game);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateGames(Game game) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(game);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteGame(Game game) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(game);
        transaction.commit();
        session.close();
    }

    @Override
    public Game getGameById(Game game) {
        return HibernateConnection.getSessionFactory().openSession().get(Game.class,game.getId());
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> gameList = HibernateConnection.getSessionFactory().openSession().createQuery("FROM Game").list();
        return gameList;
    }
}
