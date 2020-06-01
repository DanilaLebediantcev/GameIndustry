package com.epam.bh.services.serviceImpl;

import com.epam.bh.dao.PersonDAO;
import com.epam.bh.entities.Person;
import com.epam.bh.services.ServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service("PersonServiceImpl")
public class PersonServiceImpl implements ServiceDAO<Person> {
    private static final Logger log = Logger.getLogger(CompanyServiceImpl.class.getName());

    @Autowired
    private PersonDAO personDAO;

    public PersonServiceImpl(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public Person add(Person person) {
        Person addPerson = this.personDAO.save(person);
        log.info("Person " + addPerson.getName() + " was successfully added. Person details: " + addPerson.toString());
        return addPerson;
    }

    @Override
    public boolean update(Person person) {
        Optional<Person> byId = this.personDAO.findById(person.getId());
        if (!byId.isPresent()){
            return false;
        }
        this.personDAO.save(person);
        log.info("Person " + person.getName() + " was successfully added. Person details: " + person.toString());
        return true;
    }

    @Override
    public void delete(long id) {
        Optional<Person> person = this.personDAO.findById(id);
        log.info("All persons list size: " + this.personDAO.findAll().size());
        this.personDAO.deleteById(id);
        log.info("Person " + person.get().getName() + " was successfully deleted. After removing the size of the list of all persons: " + this.personDAO.findAll().size());
    }

    @Override
    public Person getById(long id) {
        Optional<Person> person = this.personDAO.findById(id);
        log.info("Person with id [" + id + "] details: " + person.get().toString());
        return person.get();
    }

    @Override
    public List<Person> getAll() {
        List<Person> personList = this.personDAO.findAll();
        log.info("All persons list size: " + personList.size());
        return personList;
    }
}
