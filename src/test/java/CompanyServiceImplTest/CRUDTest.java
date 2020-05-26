package CompanyServiceImplTest;

import com.epam.bh.entities.Company;
import com.epam.bh.entities.Game;
import com.epam.bh.entities.Genre;
import com.epam.bh.entities.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        TestBaseConfig.class
})
@TestPropertySource("classpath:application.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CRUDTest {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    void initIt() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Company ubisoft = new Company();
        ubisoft.setName("Ubisoft");

        Game assassinCreed = new Game();
        assassinCreed.setName("AC");
        assassinCreed.setCompany(ubisoft);
        Game rainbowSixSiege = new Game();
        rainbowSixSiege.setName("Rainbow Six Siege");
        rainbowSixSiege.setCompany(ubisoft);

        Person ubisoftLead = new Person();
        ubisoftLead.setName("Ubisoft Lead");
        ubisoftLead.setCompany(ubisoft);

        Genre rpg = new Genre();
        rpg.setName("RPG");
        Genre action = new Genre();
        action.setName("Action");
        Genre fpp = new Genre();
        fpp.setName("FPP");
        assassinCreed.addGenreToGame(rpg);
        assassinCreed.addGenreToGame(action);
        rainbowSixSiege.addGenreToGame(action);
        rainbowSixSiege.addGenreToGame(fpp);

        entityManager.getTransaction().begin();
        entityManager.persist(assassinCreed);
        entityManager.persist(rainbowSixSiege);
        entityManager.getTransaction().commit();


    }
}