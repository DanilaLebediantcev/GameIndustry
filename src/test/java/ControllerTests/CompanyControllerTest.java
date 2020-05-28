package ControllerTests;

import com.epam.bh.entities.Company;
import com.epam.bh.services.serviceImpl.CompanyServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.epam.bh.SpringCoreApplication.class)
@AutoConfigureMockMvc
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CompanyServiceImpl companyService;

    Company companyTest1 = new Company(1L, "Ubisoft", 1000, 800000L);
    Company companyTest2 = new Company(2L, "EA", 500, 654000L);
    Company companyTest3 = new Company(3L, "Blizzard", 4000, 46545400L);

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void addCompany() throws Exception {
        when(companyService.add(any(Company.class))).thenReturn(companyTest1);

        mockMvc.perform(MockMvcRequestBuilders.post("/companies/add").
                content(asJsonString(companyTest1)).contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }


    @Test
    public void getCompanyById() throws Exception {
        when(companyService.getById(anyLong())).thenReturn(companyTest1);
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/getById/{id}", 1).
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).
                andExpect(jsonPath("$.id").value("1")).
                andExpect(jsonPath("$.name").value("Ubisoft"));
    }


    @Test
    public void getAllCompanies() throws Exception {
        List<Company> companyList = List.of(companyTest1, companyTest2, companyTest3);
        when(companyService.getAll()).thenReturn(companyList);
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/getAll").
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }


    @Test
    public void deleteCompany() throws Exception {
        doNothing().when(companyService).delete(anyLong());
        mockMvc.perform(MockMvcRequestBuilders.delete("/companies/delete/{id}", 1).
                content(asJsonString(companyTest1)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }


    @Test
    public void updateCompany() throws Exception {
        when(companyService.update(any(Company.class))).thenReturn(true);
        companyTest1.setName(companyTest1.getName() + "_UPDATE");
        mockMvc.perform(MockMvcRequestBuilders.put("/companies/update").
                content(asJsonString(companyTest1)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

    }


}
