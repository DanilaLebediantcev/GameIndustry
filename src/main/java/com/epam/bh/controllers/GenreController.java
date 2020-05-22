package com.epam.bh.controllers;

import com.epam.bh.entities.Genre;
import com.epam.bh.services.ServiceDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/genres")
public class GenreController {

    private final ServiceDAO<Genre> genreServiceDAO;

    public GenreController(@Qualifier("genreServiceDAO") ServiceDAO<Genre> genreServiceDAO) {
        this.genreServiceDAO = genreServiceDAO;
    }

    @GetMapping(path = "/getAll")
    public List<Genre> getGenres() {
        return genreServiceDAO.getAll();

    }

    @PostMapping(path = "/add")
    public void addGenre(@RequestBody Genre genre) {
        genreServiceDAO.add(genre);
        System.out.println("----- added country from country controller: " + genre);
    }

    @GetMapping(path = "/getById/{id}")
    public Genre findGenreById(@PathVariable(name = "id") Long id) {
        return genreServiceDAO.getById(id);
    }

    @PostMapping(path = "/update")
    public void updateGenre(@RequestBody Genre genre) {
        genreServiceDAO.update(genre);
        System.out.println("----- updated country from country controller: " + genre);
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteGenre(@PathVariable(name = "id") Long id) {
        Genre deleteGenre = genreServiceDAO.getById(id);
        genreServiceDAO.delete(id);
        return deleteGenre.toString() + ". This genre was deleted. Pls, navigate to /genres/getAll";
    }
}