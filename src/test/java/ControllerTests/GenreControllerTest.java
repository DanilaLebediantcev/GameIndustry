package ControllerTests;

import com.epam.bh.entities.Genre;
import com.epam.bh.services.serviceImpl.GenreServiceImpl;
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
public class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GenreServiceImpl genreService;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static Genre genreTest1 = new Genre(1L,"RPG");
    static Genre genreTest2 = new Genre(2L,"FPP");
    static Genre genreTest3 = new Genre(3L,"Horror");

    @Test
    public void addGame() throws Exception {
        when(genreService.add(any(Genre.class))).thenReturn(genreTest1);
        mockMvc.perform(MockMvcRequestBuilders.post("/genres/add").
                content(asJsonString(genreTest1)).contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getGameById() throws Exception {
        when(genreService.getById(anyLong())).thenReturn(genreTest1);
        mockMvc.perform(MockMvcRequestBuilders.get("/genres/getById/{id}",1).
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).
                andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void getAllGames() throws Exception {
        when(genreService.getAll()).thenReturn(List.of(genreTest1,genreTest2,genreTest3));
        mockMvc.perform(MockMvcRequestBuilders.get("/genres/getAll").
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void deleteGame() throws Exception {
        doNothing().when(genreService).delete(anyLong());
        mockMvc.perform(MockMvcRequestBuilders.delete("/genres/delete/{id}", 3).
                content(asJsonString(genreTest3)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void updateGame() throws Exception {
        when(genreService.update(any(Genre.class))).thenReturn(true);
        genreTest3.setName(genreTest3.getName()+"_UPDATE");
        mockMvc.perform(MockMvcRequestBuilders.put("/genres/update").
                content(asJsonString(genreTest3)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

    }


}
