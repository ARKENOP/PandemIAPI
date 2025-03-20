package epsi.pandemia.pandemiapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import epsi.pandemia.pandemiapi.entity.Country;
import epsi.pandemia.pandemiapi.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Country testCountry;

    @BeforeEach
    public void setup() {
        countryRepository.deleteAll();
        testCountry = new Country();
        testCountry.setName("France");
        testCountry.setIso3("FR");
        testCountry = countryRepository.save(testCountry);
    }

    @Test
    public void testGetAllCountries() throws Exception {
        mockMvc.perform(get("/countries"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetCountryById() throws Exception {
        mockMvc.perform(get("/countries/" + testCountry.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("France"));
    }

    @Test
    public void testCreateCountry() throws Exception {
        Country newCountry = new Country();
        newCountry.setName("USA");
        newCountry.setIso3("US");

        mockMvc.perform(post("/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCountry)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("USA"));
    }

    @Test
    public void testUpdateCountry() throws Exception {
        testCountry.setName("Germany");

        mockMvc.perform(put("/countries/" + testCountry.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCountry)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Germany"));
    }

    @Test
    public void testDeleteCountry() throws Exception {
        mockMvc.perform(delete("/countries/" + testCountry.getId()))
                .andExpect(status().isOk());
    }
}
