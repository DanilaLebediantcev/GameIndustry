package com.epam.bh.dao;


import com.epam.bh.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonDAO extends JpaRepository<Person,Long> {

}
