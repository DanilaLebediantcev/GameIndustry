package ControllerTests;

import com.epam.bh.entities.Person;
import com.epam.bh.services.serviceImpl.PersonServiceImpl;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.epam.bh.SpringCoreApplication.class)
@AutoConfigureMockMvc
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PersonServiceImpl personService;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static Person personTest1 = new Person(1L,"Person1");
    static Person personTest2 = new Person(2L,"Person2");
    static Person personTest3 = new Person(3L,"Person3");;


    @Test
    public void addPerson() throws Exception {
        when(personService.add(any(Person.class))).thenReturn(personTest1);
        mockMvc.perform(MockMvcRequestBuilders.post("/persons/add").
                content(asJsonString(personTest1)).contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getPersonById() throws Exception {
        when(personService.getById(anyLong())).thenReturn(personTest1);
        mockMvc.perform(MockMvcRequestBuilders.get("/persons/getById/{id}",1).
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).
                andExpect(jsonPath("$.id").value("1")).
                andExpect(jsonPath("$.name").value("Person1"));
    }

    @Test
    public void getAllPersons() throws Exception {
        when(personService.getAll()).thenReturn(List.of(personTest1,personTest2,personTest3));
        mockMvc.perform(MockMvcRequestBuilders.get("/persons/getAll").
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void deletePerson() throws Exception {
        doNothing().when(personService).delete(anyLong());
        mockMvc.perform(MockMvcRequestBuilders.delete("/persons/delete/{id}", 3).
                content(asJsonString(personTest3)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void updatePerson() throws Exception {
        when(personService.update(any(Person.class))).thenReturn(true);
        personTest2.setName(personTest2.getName()+"_UPDATE");
        mockMvc.perform(MockMvcRequestBuilders.put("/persons/update").
                content(asJsonString(personTest2)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).
                andDo(print()).andExpect(status().isOk());

    }


}
