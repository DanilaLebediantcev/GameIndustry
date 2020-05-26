package com.epam.bh.services.serviceImpl;

import com.epam.bh.dao.DAO;
import com.epam.bh.entities.Person;
import com.epam.bh.services.ServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service("PersonServiceImpl")
public class PersonServiceImpl implements ServiceDAO<Person> {
    private static final Logger log = Logger.getLogger(CompanyServiceImpl.class.getName());

    private DAO<Person> personDAO;

    public PersonServiceImpl(DAO<Person> personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public void add(Person person) {
        this.personDAO.add(person);
        log.info("Person " + person.getName() + "was successfully added. Person details: " + person.toString());
    }

    @Override
    public void update(Person person) {
        this.personDAO.update(person);
        log.info("Person " + person.getName() + "was successfully added. Person details: " + person.toString());
    }

    @Override
    public void delete(long id) {
        Person person = this.personDAO.getById(id);
        log.info("All persons list size: " + this.personDAO.getAll().size());
        this.personDAO.deleteById(id);
        log.info("Person " + person.getName() + "was successfully deleted. After removing the size of the list of all persons: " + this.personDAO.getAll().size());
    }

    @Override
    public Person getById(long id) {
        Person person = this.personDAO.getById(id);
        log.info("Person with id [" + id + "] details: " + person.toString());
        return this.personDAO.getById(id);
    }

    @Override
    public List<Person> getAll() {
        List<Person> personList = this.personDAO.getAll();
        log.info("All persons list size: " + personList.size());
        return personList;
    }
}
