//package com.epam.bh.configuration;
//
//import com.epam.bh.converter.Converter;
//import com.epam.bh.dao.CompanyDAO;
//import com.epam.bh.entities.Company;
//import com.epam.bh.entities.Game;
//import com.epam.bh.entities.Genre;
//import com.epam.bh.entities.Person;
//import com.epam.bh.services.ServiceDAO;
//import com.epam.bh.services.serviceImpl.CompanyServiceImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import javax.persistence.EntityManagerFactory;
//
//@Configuration
//public class ApplicationConfiguration {
//    private Environment environment;
//
//    public ApplicationConfiguration(Environment environment) {
//        this.environment = environment;
//    }
//
//    @Bean
//    public CompanyDAO companyDAO(EntityManagerFactory entityManagerFactory) {
//        return new CompanyDAOImpl(entityManagerFactory);
//    }
//
//    @Bean
//    public CompanyDAO<Game> gameDAO(EntityManagerFactory entityManagerFactory) {
//        return new GameDAOImpl(entityManagerFactory);
//    }
//
//    @Bean
//    public CompanyDAO<Genre> genreDAO(EntityManagerFactory entityManagerFactory) {
//        return new GenreDAOImpl(entityManagerFactory);
//    }
//
//    @Bean
//    public CompanyDAO<Person> personDAO(EntityManagerFactory entityManagerFactory) {
//        return new PersonDAOImpl(entityManagerFactory);
//    }
//
//    @Bean
//    public ServiceDAO<Company> companyServiceDAO(CompanyDAO<Company> companyDAO) {
//        return new CompanyServiceImpl(companyDAO);
//    }
//
//    @Bean
//    public ServiceDAO<Game> gameServiceDAO(CompanyDAO<Game> gameDAO) {
//        return new GameServiceImpl(gameDAO);
//    }
//
//    @Bean
//    public ServiceDAO<Genre> genreServiceDAO(CompanyDAO<Genre> genreDAO) {
//        return new GenreServiceImpl(genreDAO);
//    }
//
//    @Bean
//    public ServiceDAO<Person> personServiceDAO(CompanyDAO<Person> personDAO) {
//        return new PersonServiceImpl(personDAO);
//    }
//
//    @Bean
//    public Converter getConverter(){
//        return new Converter();
//    }
//
//}
