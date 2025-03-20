package epsi.pandemia.pandemiapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import epsi.pandemia.pandemiapi.entity.Region;
import epsi.pandemia.pandemiapi.repository.RegionRepository;
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
public class RegionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Region testRegion;

    @BeforeEach
    public void setup() {
        regionRepository.deleteAll();
        testRegion = new Region();
        testRegion.setName("Ile-de-France");
        testRegion = regionRepository.save(testRegion);
    }

    @Test
    public void testGetAllRegions() throws Exception {
        mockMvc.perform(get("/regions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetRegionById() throws Exception {
        mockMvc.perform(get("/regions/" + testRegion.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ile-de-France"));
    }

    @Test
    public void testCreateRegion() throws Exception {
        Region newRegion = new Region();
        newRegion.setName("Bavaria");

        mockMvc.perform(post("/regions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newRegion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bavaria"));
    }

    @Test
    public void testUpdateRegion() throws Exception {
        testRegion.setName("Normandy");

        mockMvc.perform(put("/regions/" + testRegion.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testRegion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Normandy"));
    }

    @Test
    public void testDeleteRegion() throws Exception {
        mockMvc.perform(delete("/regions/" + testRegion.getId()))
                .andExpect(status().isOk());
    }
}
