package com.epam.bh.controllers;


import com.epam.bh.entities.Person;
import com.epam.bh.services.ServiceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/persons")
public class PersonController {

    private final ServiceDAO<Person> personServiceDAO;
    private Logger log = LoggerFactory.getLogger(PersonController.class);

    public PersonController(@Qualifier("personServiceDAO") ServiceDAO<Person> personServiceDAO) {
        this.personServiceDAO = personServiceDAO;
    }

    @GetMapping(path = "/getAll")
    public List<Person> getGenres() {
        return personServiceDAO.getAll();

    }

    @PostMapping(path = "/add")
    public void addGenre(@RequestBody Person person) {
        log.info("----- added country from country controller: " + person);
    }

    @GetMapping(path = "/getById/{id}")
    public Person findGenreById(@PathVariable(name = "id") Long id) {
        return personServiceDAO.getById(id);
    }

    @PostMapping(path = "/update")
    public void updateGenre(@RequestBody Person person) {
        personServiceDAO.update(person);
        log.info("----- updated country from country controller: " + person);
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteGenre(@PathVariable(name = "id") Long id) {
        Person deleteGenre = personServiceDAO.getById(id);
        personServiceDAO.delete(id);
        return deleteGenre.toString() + ". This person was deleted. Pls, navigate to /persons/getAll";
    }
}