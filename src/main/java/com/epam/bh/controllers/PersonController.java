package com.epam.bh.controllers;


import com.epam.bh.entities.Person;
import com.epam.bh.services.ServiceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/persons")
public class PersonController {

    private final ServiceDAO<Person> personServiceDAO;

    private Logger log = LoggerFactory.getLogger(CompanyController.class);

    public PersonController(ServiceDAO<Person> personServiceDAO) {
        this.personServiceDAO = personServiceDAO;
    }

    @GetMapping(path = "/getAll")
    public List<Person> getGenres() {
        return personServiceDAO.getAll();

    }

    @PostMapping(path = "/add")
    public void addGenre(@RequestBody Person person) {
        log.info("----- added person from person controller: " + person);
    }

    @GetMapping(path = "/getById/{id}")
    public Person findGenreById(@PathVariable(name = "id") Long id) {
        Person findByIdPerson = personServiceDAO.getById(id);
        log.info("----- !!!!!! Genre found: " + findByIdPerson.toString());
        return findByIdPerson;
    }

    @PutMapping(path = "/update")
    public void updateGenre(@RequestBody Person person) {
        personServiceDAO.update(person);
        log.info("----- updated person from person controller: " + person);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteGenre(@PathVariable(name = "id") Long id) {
        personServiceDAO.delete(id);
        log.info("----- Person with id [" + id + "] was deleted. Pls, navigate to /persons/getAll");
    }
}