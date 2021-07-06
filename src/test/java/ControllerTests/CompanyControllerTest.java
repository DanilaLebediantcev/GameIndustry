package ControllerTests;

import CompanyServiceImplTest.TestBaseConfig;
import com.epam.bh.controllers.CompanyController;
import com.epam.bh.entities.Company;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.persistence.EntityManagerFactory;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes =  {TestBaseConfig.class,CompanyController.class})
@WebAppConfiguration
@EnableWebMvc
@TestPropertySource("classpath:application.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CompanyControllerTest {
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
    static Company companyTest2 = new Company();

    static {
        companyTest1.setName("Company1FromCompanyControllerTest");
        companyTest1.setNumberOfEmployees(1000);
        companyTest1.setProfit(60000000L);

        companyTest2.setName("Company2FromCompanyControllerTest");
        companyTest2.setNumberOfEmployees(6000);
        companyTest2.setProfit(10000L);
    }


    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        entityManager.getTransaction().begin();
        entityManager.persist(companyTest1);
        entityManager.persist(companyTest2);
        entityManager.getTransaction().commit();
    }

    @AfterAll
    public void endTest(){
        entityManager.close();
        entityManagerFactory.close();
    }

    @SneakyThrows
    @Test
    public void addCompany() {
        Company companyTestAddControllerMethod = new Company();
        companyTestAddControllerMethod.setName("CompanyControllerTestMethodAdd");
        mockMvc.perform(MockMvcRequestBuilders.post("/companies/add").
                content(asJsonString(companyTestAddControllerMethod)).contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void getCompanyById(){
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/getById/{id}",1).
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).
                andExpect(jsonPath("$.name").value("Company1FromCompanyControllerTest"));
    }

    @SneakyThrows
    @Test
    public void getAllCompany(){
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/getAll").
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void deleteCompany() {
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/delete/{id}",2).
                content(asJsonString(companyTest2)).contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void updateCompany() {
        companyTest1.setName(companyTest1.getName()+"_UPDATE");
        mockMvc.perform(MockMvcRequestBuilders.post("/companies/update").
                content(asJsonString(companyTest1)).contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }


}
