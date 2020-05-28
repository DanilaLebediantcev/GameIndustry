package ConverterTest;

import com.epam.bh.converter.Converter;
import com.epam.bh.entities.Company;
import com.epam.bh.entities.Game;
import com.epam.bh.services.ServiceDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.epam.bh.SpringCoreApplication.class)
@TestPropertySource("classpath:test.properties")
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

    @Test
    public void TestParse() throws JSONException {

        List<Game> games = getConverter.readObjectsFromJsonFile("D:\\EPAM_project\\GameIndustry\\src\\test\\java\\ConverterTest\\JsonFileForRead.json", Game.class);


        entityManager = entityManagerFactory.createEntityManager();
        for (Game game : games) {
            entityManager.getTransaction().begin();
            entityManager.persist(game);
            entityManager.getTransaction().commit();
        }

        Game game1 = entityManager.createNamedQuery("Game.getById", Game.class).setParameter("id", 1L).getSingleResult();
        //после занесения из json объектов в базу заправшиваем один из них
        Assert.assertNotNull(game1);
        Game game2 = entityManager.createNamedQuery("Game.getById", Game.class).setParameter("id", 2L).getSingleResult();
        List<Game> gameList = List.of(game1,game2);
        entityManager.close();

        getConverter.writeObjectsToJsonFile(gameList,"D:\\EPAM_project\\GameIndustry\\src\\test\\java\\ConverterTest\\OutputFileWithGames.json");

        List<Company> companyList = companyServiceDAO.getAll();
        getConverter.writeObjectsToJsonFile(companyList,"D:\\EPAM_project\\GameIndustry\\src\\test\\java\\ConverterTest\\OutputFileWithCompanies.json");

    }
}


