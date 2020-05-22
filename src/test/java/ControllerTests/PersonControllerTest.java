package ControllerTests;

import CompanyServiceImplTest.TestBaseConfig;
import com.epam.bh.controllers.GameController;
import com.epam.bh.controllers.PersonController;
import com.epam.bh.entities.Company;
import com.epam.bh.entities.Game;
import com.epam.bh.entities.Person;
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
@ContextConfiguration(classes =  {TestBaseConfig.class, PersonController.class})
@WebAppConfiguration
@EnableWebMvc
@TestPropertySource("classpath:application.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonControllerTest {
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

    static Person personTest1 = new Person();
    static Person personTest2 = new Person();
    static Person personTest3 = new Person();

    static {
       personTest1.setName("PersonTest1");
       personTest2.setName("PersonTest2");
       personTest3.setName("PersonTest3");
    }


    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        entityManager.getTransaction().begin();
        entityManager.persist(personTest1);
        entityManager.persist(personTest2);
        entityManager.persist(personTest3);
        entityManager.getTransaction().commit();
    }

    @AfterAll
    public void endTest(){
        entityManager.close();
        entityManagerFactory.close();

    }

    @SneakyThrows
    @Test
    public void addPerson() {
        Person personTestAddControllerMethod = new Person();
        personTestAddControllerMethod.setName("PersonControllerTestMethodAdd");
        mockMvc.perform(MockMvcRequestBuilders.post("/persons/add").
                content(asJsonString(personTestAddControllerMethod)).contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void getPersonById(){
        mockMvc.perform(MockMvcRequestBuilders.get("/persons/getById/{id}",1).
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).
                andExpect(jsonPath("$.id").value("1"));
    }

    @SneakyThrows
    @Test
    public void getAllPersons(){
        mockMvc.perform(MockMvcRequestBuilders.get("/persons/getAll").
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void deletePerson() {
        mockMvc.perform(MockMvcRequestBuilders.get("/persons/delete/{id}", 2).
                content(asJsonString(personTest2)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void updatePerson() {
        personTest3.setName(personTest3.getName()+"_UPDATE");
        mockMvc.perform(MockMvcRequestBuilders.post("/persons/update").
                content(asJsonString(personTest3)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

    }


}
