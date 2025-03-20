package epsi.pandemia.pandemiapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import epsi.pandemia.pandemiapi.entity.Continent;
import epsi.pandemia.pandemiapi.repository.ContinentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ContinentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContinentRepository continentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Continent testContinent;

    @BeforeEach
    public void setup() {
        continentRepository.deleteAll();
        testContinent = new Continent();
        testContinent.setName("Europe");
        testContinent = continentRepository.save(testContinent);
    }

    @Test
    public void testGetAllContinents() throws Exception {
        mockMvc.perform(get("/continents"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetContinentById() throws Exception {
        mockMvc.perform(get("/continents/" + testContinent.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Europe"));
    }

    @Test
    public void testCreateContinent() throws Exception {
        Continent newContinent = new Continent();
        newContinent.setName("Asia");

        mockMvc.perform(post("/continents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newContinent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Asia"));
    }

    @Test
    public void testUpdateContinent() throws Exception {
        testContinent.setName("Africa");

        mockMvc.perform(put("/continents/" + testContinent.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testContinent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Africa"));
    }

    @Test
    public void testDeleteContinent() throws Exception {
        mockMvc.perform(delete("/continents/" + testContinent.getId()))
                .andExpect(status().isOk());
    }
}
