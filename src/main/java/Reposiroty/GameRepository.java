package Reposiroty;

import dao.DAO;
import db.HibernateConnection;
import entity.Game;
import entity.Genre;
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
        for (Genre genre : game.getGenreList()) {
            genre.getGameList().remove(game);
            game.getGenreList().remove(genre);
            session.update(game);
            session.update(genre);
        }
        session.delete(game);
        transaction.commit();
        session.close();
    }

    @Override
    public Game getById(Long id) {
        return HibernateConnection.getSessionFactory().openSession().get(Game.class, id);
    }

    @Override
    public List<Game> getAll() {
        List<Game> gameList = HibernateConnection.getSessionFactory().openSession().createQuery("FROM Game").list();
        return gameList;
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
