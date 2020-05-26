package com.epam.bh.configuration;

import com.epam.bh.dao.DAO;
import com.epam.bh.dao.daoImpl.CompanyDAOImpl;
import com.epam.bh.dao.daoImpl.GameDAOImpl;
import com.epam.bh.dao.daoImpl.GenreDAOImpl;
import com.epam.bh.dao.daoImpl.PersonDAOImpl;
import com.epam.bh.entities.Company;
import com.epam.bh.entities.Game;
import com.epam.bh.entities.Genre;
import com.epam.bh.entities.Person;
import com.epam.bh.services.ServiceDAO;
import com.epam.bh.services.serviceImpl.CompanyServiceImpl;
import com.epam.bh.services.serviceImpl.GameServiceImpl;
import com.epam.bh.services.serviceImpl.GenreServiceImpl;
import com.epam.bh.services.serviceImpl.PersonServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.persistence.EntityManagerFactory;

@Configuration
public class ApplicationConfiguration {
    private Environment environment;

    public ApplicationConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DAO<Company> companyDAO(EntityManagerFactory entityManagerFactory) {
        return new CompanyDAOImpl(entityManagerFactory);
    }

    @Bean
    public DAO<Game> gameDAO(EntityManagerFactory entityManagerFactory) {
        return new GameDAOImpl(entityManagerFactory);
    }

    @Bean
    public DAO<Genre> genreDAO(EntityManagerFactory entityManagerFactory) {
        return new GenreDAOImpl(entityManagerFactory);
    }

    @Bean
    public DAO<Person> personDAO(EntityManagerFactory entityManagerFactory) {
        return new PersonDAOImpl(entityManagerFactory);
    }

    @Bean
    public ServiceDAO<Company> companyServiceDAO(DAO<Company> companyDAO) {
        return new CompanyServiceImpl(companyDAO);
    }

    @Bean
    public ServiceDAO<Game> gameServiceDAO(DAO<Game> gameDAO) {
        return new GameServiceImpl(gameDAO);
    }

    @Bean
    public ServiceDAO<Genre> genreServiceDAO(DAO<Genre> genreDAO) {
        return new GenreServiceImpl(genreDAO);
    }

    @Bean
    public ServiceDAO<Person> personServiceDAO(DAO<Person> personDAO) {
        return new PersonServiceImpl(personDAO);
    }

}
