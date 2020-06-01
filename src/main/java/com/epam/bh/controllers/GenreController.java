package com.epam.bh.controllers;

import com.epam.bh.entities.Genre;
import com.epam.bh.services.ServiceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/genres")
public class GenreController {

    private final ServiceDAO<Genre> genreServiceDAO;

    private Logger log = LoggerFactory.getLogger(GameController.class);

    public GenreController(ServiceDAO<Genre> genreServiceDAO) {
        this.genreServiceDAO = genreServiceDAO;
    }

    @GetMapping(path = "/getAll")
    public List<Genre> getGenres() {
        return genreServiceDAO.getAll();

    }

    @PostMapping(path = "/add")
    public void addGenre(@RequestBody Genre genre) {
        genreServiceDAO.add(genre);
        log.info("----- added genre from genre controller: " + genre);
    }

    @GetMapping(path = "/getById/{id}")
    public Genre findGenreById(@PathVariable(name = "id") Long id) {
        Genre findByIdGenre = genreServiceDAO.getById(id);
        log.info("----- !!!!!! Genre found: " + findByIdGenre.toString());
        return findByIdGenre;
    }

    @PutMapping(path = "/update")
    public void updateGenre(@RequestBody Genre genre) {
        genreServiceDAO.update(genre);
        log.info("----- updated genre from genre controller: " + genre);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteGenre(@PathVariable(name = "id") Long id) {
        genreServiceDAO.delete(id);
        log.info("----- Genre with id [" + id + "] was deleted. Pls, navigate to /genres/getAll");
    }
}