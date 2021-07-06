package com.epam.bh.controllers;

import com.epam.bh.entities.Game;
import com.epam.bh.services.ServiceDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/games")
public class GameController {

    private final ServiceDAO<Game> gameServiceDAO;

    public GameController(@Qualifier("gameServiceDAO") ServiceDAO<Game> gameServiceDAO) {
        this.gameServiceDAO = gameServiceDAO;
    }

    @GetMapping(path = "/getAll")
    public List<Game> getGames() {
        return gameServiceDAO.getAll();

    }

    @PostMapping(path = "/add")
    public void addGame(@RequestBody Game game) {
        gameServiceDAO.add(game);
        System.out.println("----- added country from country controller: " + game);
    }

    @GetMapping(path = "/getById/{id}")
    public Game findGameById(@PathVariable(name = "id") Long id) {
        return gameServiceDAO.getById(id);
    }

    @PostMapping(path = "/update")
    public void updateGame(@RequestBody Game game) {
        gameServiceDAO.update(game);
        System.out.println("----- updated country from country controller: " + game);
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteGame(@PathVariable(name = "id") Long id) {
        Game deleteGame = gameServiceDAO.getById(id);
        gameServiceDAO.delete(id);
        return deleteGame.toString() + ". This game was deleted. Pls, navigate to /games/getAll";
    }
}