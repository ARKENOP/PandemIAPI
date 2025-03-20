package epsi.pandemia.pandemiapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import epsi.pandemia.pandemiapi.entity.GlobalData;
import epsi.pandemia.pandemiapi.repository.GlobalDataRepository;
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
public class GlobalDataControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GlobalDataRepository globalDataRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private GlobalData testGlobalData;

    @BeforeEach
    public void setup() {
        globalDataRepository.deleteAll();
        testGlobalData = new GlobalData();
        testGlobalData.setTotalCases(1000000);
        testGlobalData = globalDataRepository.save(testGlobalData);
    }

    @Test
    public void testGetAllGlobalData() throws Exception {
        mockMvc.perform(get("/globaldata"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetGlobalDataById() throws Exception {
        mockMvc.perform(get("/globaldata/" + testGlobalData.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCases").value(1000000));
    }

    @Test
    public void testCreateGlobalData() throws Exception {
        GlobalData newGlobalData = new GlobalData();
        newGlobalData.setTotalCases(2000000);

        mockMvc.perform(post("/globaldata")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newGlobalData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCases").value(2000000));
    }

    @Test
    public void testUpdateGlobalData() throws Exception {
        testGlobalData.setTotalCases(1500000);

        mockMvc.perform(put("/globaldata/" + testGlobalData.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testGlobalData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCases").value(1500000));
    }

    @Test
    public void testDeleteGlobalData() throws Exception {
        mockMvc.perform(delete("/globaldata/" + testGlobalData.getId()))
                .andExpect(status().isOk());
    }
}
