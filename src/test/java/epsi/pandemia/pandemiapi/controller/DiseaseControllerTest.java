package epsi.pandemia.pandemiapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import epsi.pandemia.pandemiapi.entity.Disease;
import epsi.pandemia.pandemiapi.repository.DiseaseRepository;
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
public class DiseaseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Disease testDisease;

    @BeforeEach
    public void setup() {
        diseaseRepository.deleteAll();
        testDisease = new Disease();
        testDisease.setName("COVID-19");
        testDisease = diseaseRepository.save(testDisease);
    }

    @Test
    public void testGetAllDiseases() throws Exception {
        mockMvc.perform(get("/diseases"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetDiseaseById() throws Exception {
        mockMvc.perform(get("/diseases/" + testDisease.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("COVID-19"));
    }

    @Test
    public void testCreateDisease() throws Exception {
        Disease newDisease = new Disease();
        newDisease.setName("Influenza");

        mockMvc.perform(post("/diseases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newDisease)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Influenza"));
    }

    @Test
    public void testUpdateDisease() throws Exception {
        testDisease.setName("SARS");

        mockMvc.perform(put("/diseases/" + testDisease.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testDisease)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("SARS"));
    }

    @Test
    public void testDeleteDisease() throws Exception {
        mockMvc.perform(delete("/diseases/" + testDisease.getId()))
                .andExpect(status().isOk());
    }
}
