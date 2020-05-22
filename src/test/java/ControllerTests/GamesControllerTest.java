package ControllerTests;

import CompanyServiceImplTest.TestBaseConfig;
import com.epam.bh.controllers.GameController;
import com.epam.bh.entities.Company;
import com.epam.bh.entities.Game;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes =  {TestBaseConfig.class, GameController.class})
@WebAppConfiguration
@EnableWebMvc
@TestPropertySource("classpath:application.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GamesControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private WebApplicationContext webApplicationContext;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static Company companyTest1 = new Company();
    static Game gameTest1 = new Game();
    static Game gameTest2 = new Game();
    static Game gameTest3 = new Game();

    static {
        companyTest1.setName("Company3FromCompanyControllerTest");
        companyTest1.setNumberOfEmployees(1000);
        companyTest1.setProfit(60000000L);

        gameTest1.setName("GameTest1");
        gameTest1.setCompany(companyTest1);
        gameTest2.setName("GameTest2");
        gameTest2.setCompany(companyTest1);
        gameTest3.setName("GameTest3");
        gameTest3.setCompany(companyTest1);
    }


    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        entityManager.getTransaction().begin();
        entityManager.persist(companyTest1);
        entityManager.persist(gameTest1);
        entityManager.persist(gameTest2);
        entityManager.getTransaction().commit();
    }

    @AfterAll
    public void endTest(){
        entityManager.close();
        entityManagerFactory.close();

    }

    @SneakyThrows
    @Test
    public void addGame() {
        Game gameTestAddControllerMethod = new Game();
        gameTestAddControllerMethod.setName("GameControllerTestMethodAdd");
        gameTestAddControllerMethod.setCompany(companyTest1);
        mockMvc.perform(MockMvcRequestBuilders.post("/games/add").
                content(asJsonString(gameTestAddControllerMethod)).contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void getGameById(){
        mockMvc.perform(MockMvcRequestBuilders.get("/games/getById/{id}",1).
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).
                andExpect(jsonPath("$.id").value("1")).
                andExpect(jsonPath("$.company.name").value("Company3FromCompanyControllerTest"));
    }

    @SneakyThrows
    @Test
    public void getAllGames(){
        mockMvc.perform(MockMvcRequestBuilders.get("/games/getAll").
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void deleteGame() {
        mockMvc.perform(MockMvcRequestBuilders.get("/games/delete/{id}", 2).
                content(asJsonString(gameTest2)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void updateGame() {
        gameTest3.setName(gameTest3.getName()+"_UPDATE");
        mockMvc.perform(MockMvcRequestBuilders.post("/games/update").
                content(asJsonString(gameTest3)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

    }


}
