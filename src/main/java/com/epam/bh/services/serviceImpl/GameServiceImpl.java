package com.epam.bh.services.serviceImpl;

import com.epam.bh.dao.DAO;
import com.epam.bh.entities.Game;
import com.epam.bh.services.ServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service("GameServiceImpl")
public class GameServiceImpl implements ServiceDAO<Game> {

    private static final Logger log = Logger.getLogger(CompanyServiceImpl.class.getName());
    
    @Autowired
    private DAO<Game> gameDAO;

    public GameServiceImpl(DAO<Game> gameDAO) {
        this.gameDAO = gameDAO;
    }

    @Override
    public void add(Game game) {
        this.gameDAO.add(game);
        log.info("Game " + game.getName() + "was successfully added. Game details: " + game.toString());
    }

    @Override
    public void update(Game game) {
        this.gameDAO.update(game);
        log.info("Game " + game.getName() + "was successfully updated. Game details: " + game.toString());
    }

    @Override
    public void delete(long id) {
        Game game = this.gameDAO.getById(id);
        log.info("All games list size: " + this.gameDAO.getAll().size());
        this.gameDAO.deleteById(id);
        log.info("Game " + game.getName() + "was successfully deleted. After removing the size of the list of all games: " + this.gameDAO.getAll().size());
    }

    @Override
    public Game getById(long id) {
        Game game = this.gameDAO.getById(id);
        log.info("Game with id [" + id + "] details: " + game.toString());
        return game;
    }

    @Override
    public List<Game> getAll() {
        List<Game> gameList = this.gameDAO.getAll();
        log.info("All games list size: " + gameList.size());
        return gameList;
    }
}
