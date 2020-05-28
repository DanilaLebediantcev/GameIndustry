package com.epam.bh.controllers;

import com.epam.bh.entities.Game;
import com.epam.bh.services.ServiceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/games")
public class GameController {

    private final ServiceDAO<Game> gameServiceDAO;

    private Logger log = LoggerFactory.getLogger(GameController.class);

    public GameController(ServiceDAO<Game> gameServiceDAO) {
        this.gameServiceDAO = gameServiceDAO;
    }

    @GetMapping(path = "/getAll")
    public List<Game> getGames() {
        return gameServiceDAO.getAll();

    }

    @PostMapping(path = "/add")
    public void addGame(@RequestBody Game game) {
        gameServiceDAO.add(game);
        log.info("----- added game from game controller: " + game);
    }

    @GetMapping(path = "/getById/{id}")
    public Game findGameById(@PathVariable(name = "id") Long id) {
        Game findByIdGame = gameServiceDAO.getById(id);
        log.info("----- !!!!!! Game found: " + findByIdGame.toString());
        return findByIdGame;
    }

    @PutMapping(path = "/update")
    public void updateGame(@RequestBody Game game) {
        gameServiceDAO.update(game);
        log.info("----- updated game from game controller: " + game);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteGame(@PathVariable(name = "id") Long id) {
        gameServiceDAO.delete(id);
        log.info("----- Game with id [" + id + "] was deleted. Pls, navigate to /games/getAll");
    }
}