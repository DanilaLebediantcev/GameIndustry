package dao;

import entity.Person;

import java.util.List;

public interface PersonDAO {
    public void addPerson(Person person);
    public void updatePerson(Person person);
    public Person getPersonById(Person person);
    public List<Person> getAllPersons();
    public void deletePerson(Person person);
}
