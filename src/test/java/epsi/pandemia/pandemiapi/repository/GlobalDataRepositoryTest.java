package epsi.pandemia.pandemiapi.repository;

import epsi.pandemia.pandemiapi.entity.GlobalData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class GlobalDataRepositoryTest {
    @Autowired
    private GlobalDataRepository globalDataRepository;

    @Test
    public void testSaveGlobalData() {
        GlobalData globalData = new GlobalData();
        globalData.setTotalCases(1000000);

        GlobalData savedGlobalData = globalDataRepository.save(globalData);
        assertNotNull(savedGlobalData.getId());
        assertEquals(1000000, savedGlobalData.getTotalCases());
    }

    @Test
    public void testFindById() {
        GlobalData globalData = new GlobalData();
        globalData.setTotalCases(500000);
        globalData = globalDataRepository.save(globalData);

        GlobalData found = globalDataRepository.findById(globalData.getId()).orElse(null);
        assertNotNull(found);
        assertEquals(500000, found.getTotalCases());
    }
}
