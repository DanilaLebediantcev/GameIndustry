package ConverterTest;

import CompanyServiceImplTest.TestBaseConfig;
import com.epam.bh.converter.Converter;
import com.epam.bh.entities.Company;
import com.epam.bh.entities.Game;
import com.epam.bh.services.ServiceDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        TestBaseConfig.class
})
@TestPropertySource("classpath:application.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@WebMvcTest
@JsonTest
public class ConverterTest {
    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    EntityManager entityManager;

    @Autowired
    Converter getConverter;

    ObjectMapper mapper = new ObjectMapper();


    @Autowired
    ServiceDAO<Company> companyServiceDAO;

    @SneakyThrows
    @Test
    public void TestParse() throws JSONException {

        List<Game> games = getConverter.readObjectsFromJsonFile(".\\src\\test\\java\\ConverterTest\\JsonFileForRead.json", Game.class);


        entityManager = entityManagerFactory.createEntityManager();
        for (Game game : games) {
            entityManager.getTransaction().begin();
            entityManager.persist(game);
            entityManager.getTransaction().commit();
        }

        Game game1 = entityManager.createNamedQuery("Game.getById", Game.class).setParameter("id", 1L).getSingleResult();
        //после занесения из json объектов в базу запрвшиваем один из них
        Assert.assertNotNull(game1);
        Game game2 = entityManager.createNamedQuery("Game.getById", Game.class).setParameter("id", 2L).getSingleResult();
        List<Game> gameList = List.of(game1,game2);
        entityManager.close();

        getConverter.writeObjectsToJsonFile(gameList,".\\src\\test\\java\\ConverterTest\\OutputFileWithGames.json");

        List<Company> companyList = companyServiceDAO.getAll();
        getConverter.writeObjectsToJsonFile(companyList,".\\src\\test\\java\\ConverterTest\\OutputFileWithCompanies.json");

    }
}


