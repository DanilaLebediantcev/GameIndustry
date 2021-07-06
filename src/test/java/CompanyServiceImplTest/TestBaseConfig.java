package CompanyServiceImplTest;

import com.epam.bh.converter.Converter;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Objects;
import java.util.Properties;

@Configuration
public class TestBaseConfig {
    @Autowired
    private Environment env;

    @Bean
    public EntityManager entityManager() {
        return Objects.requireNonNull(entityManagerFactory().getObject()).createEntityManager();
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

    @Bean
    public Converter getConverter(){
        return new Converter();
    }


    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
        entityManagerFactory.setPackagesToScan("com.epam.bh");
        entityManagerFactory.setJpaProperties(hibernateJpaProperties());
        return entityManagerFactory;
    }

    private Properties hibernateJpaProperties() {
        Properties props = new Properties();
        props.put(org.hibernate.cfg.Environment.DRIVER, env.getProperty("spring.datasource.driverClassName"));
        props.put(org.hibernate.cfg.Environment.URL, env.getProperty("spring.datasource.url"));
        props.put(org.hibernate.cfg.Environment.SHOW_SQL, "true");
        props.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
        props.put(org.hibernate.cfg.Environment.USER, env.getProperty("spring.datasource.username"));
        props.put(org.hibernate.cfg.Environment.PASS, env.getProperty("spring.datasource.password"));
        return props;
    }
}