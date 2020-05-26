package ControllerTests;

import CompanyServiceImplTest.TestBaseConfig;
import com.epam.bh.controllers.GenreController;
import com.epam.bh.entities.Genre;
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
@ContextConfiguration(classes =  {TestBaseConfig.class, GenreController.class})
@WebAppConfiguration
@EnableWebMvc
@TestPropertySource("classpath:test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GenreControllerTest {
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

    static Genre genreTest1 = new Genre();
    static Genre genreTest2 = new Genre();
    static Genre genreTest3 = new Genre();

    static {
        genreTest1.setName("GenreTest1");
        genreTest2.setName("GenreTest2");
        genreTest3.setName("GenreTest3");
    }


    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        entityManager.getTransaction().begin();
        entityManager.persist(genreTest1);
        entityManager.persist(genreTest2);
        entityManager.persist(genreTest3);
        entityManager.getTransaction().commit();
    }

    @AfterAll
    public void endTest(){
        entityManager.close();
        entityManagerFactory.close();

    }

    
    @Test
    public void addGame() throws Exception {
        Genre genreTestAddControllerMethod = new Genre();
        genreTestAddControllerMethod.setName("GenreControllerTestMethodAdd");
        mockMvc.perform(MockMvcRequestBuilders.post("/genres/add").
                content(asJsonString(genreTestAddControllerMethod)).contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    
    @Test
    public void getGameById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/genres/getById/{id}",1).
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).
                andExpect(jsonPath("$.id").value("1"));
    }

    
    @Test
    public void getAllGames() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/genres/getAll").
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    
    @Test
    public void deleteGame() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/genres/delete/{id}", 2).
                content(asJsonString(genreTest2)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    
    @Test
    public void updateGame() throws Exception {
        genreTest3.setName(genreTest3.getName()+"_UPDATE");
        mockMvc.perform(MockMvcRequestBuilders.post("/genres/update").
                content(asJsonString(genreTest3)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

    }


}
