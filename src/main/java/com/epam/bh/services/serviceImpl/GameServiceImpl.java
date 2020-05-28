package com.epam.bh.services.serviceImpl;

import com.epam.bh.dao.GameDAO;
import com.epam.bh.entities.Game;
import com.epam.bh.services.ServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service("GameServiceImpl")
public class GameServiceImpl implements ServiceDAO<Game> {

    private static final Logger log = Logger.getLogger(CompanyServiceImpl.class.getName());

    @Autowired
    private GameDAO gameDAO;

    public GameServiceImpl(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }

    @Override
    public Game add(Game game) {
        Game addGame = this.gameDAO.save(game);
        log.info("Game " + game.getName() + " was successfully added. Game details: " + game.toString());
        return addGame;
    }

    @Override
    public boolean update(Game game) {
        Optional<Game> byId = this.gameDAO.findById(game.getId());
        if(!byId.isPresent()){
            return false;
        }
        this.gameDAO.save(game);
        log.info("Game " + game.getName() + " was successfully updated. Game details: " + game.toString());
        return true;
    }

    @Override
    public void delete(long id) {
        Optional<Game> game = this.gameDAO.findById(id);
        log.info("All games list size: " + this.gameDAO.findAll().size());
        this.gameDAO.deleteById(id);
        log.info("Game " + game.get().getName() + " was successfully deleted. After removing the size of the list of all games: " + this.gameDAO.findAll().size());
    }

    @Override
    public Game getById(long id) {
        Optional<Game> game = this.gameDAO.findById(id);
        log.info("Game with id [" + id + "] details: " + game.get().toString());
        return game.get();
    }

    @Override
    public List<Game> getAll() {
        List<Game> gameList = this.gameDAO.findAll();
        log.info("All games list size: " + gameList.size());
        return gameList;
    }
}
