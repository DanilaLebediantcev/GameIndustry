package ControllerTests;

import com.epam.bh.entities.Company;
import com.epam.bh.entities.Game;
import com.epam.bh.services.serviceImpl.GameServiceImpl;
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
public class GamesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GameServiceImpl gameService;

    Company companyTest1 = new Company(1L, "Ubisoft", 1000, 800000L);


    Game gameTest1 = new Game(1L,"Assassins Creed",companyTest1);
    Game gameTest2 = new Game(2L,"Rainbow Six Siege",companyTest1);
    Game gameTest3 = new Game(3L,"Anno 2025",companyTest1);


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addGame() throws Exception {
        when(gameService.add(any(Game.class))).thenReturn(gameTest1);
        mockMvc.perform(MockMvcRequestBuilders.post("/games/add").
                content(asJsonString(gameTest1)).contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }


    @Test
    public void getGameById() throws Exception {
        when(gameService.getById(anyLong())).thenReturn(gameTest1);
        mockMvc.perform(MockMvcRequestBuilders.get("/games/getById/{id}",1).
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).
                andExpect(jsonPath("$.id").value("1")).
                andExpect(jsonPath("$.company.name").value("Ubisoft"));
    }


    @Test
    public void getAllGames() throws Exception {
        when(gameService.getAll()).thenReturn(List.of(gameTest1,gameTest2,gameTest3));
        mockMvc.perform(MockMvcRequestBuilders.get("/games/getAll").
                accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }


    @Test
    public void deleteGame() throws Exception {
        doNothing().when(gameService).delete(anyLong());
        mockMvc.perform(MockMvcRequestBuilders.delete("/games/delete/{id}", 3).
                content(asJsonString(gameTest3)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }


    @Test
    public void updateGame() throws Exception {
        when(gameService.update(any(Game.class))).thenReturn(true);
        gameTest2.setName(gameTest2.getName()+"_UPDATE");
        mockMvc.perform(MockMvcRequestBuilders.put("/games/update").
                content(asJsonString(gameTest2)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

    }


}
