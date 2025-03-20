package epsi.pandemia.pandemiapi.repository;

import epsi.pandemia.pandemiapi.entity.Region;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class RegionRepositoryTest {
    @Autowired
    private RegionRepository regionRepository;

    @Test
    public void testSaveRegion() {
        Region region = new Region();
        region.setName("Ile-de-France");

        Region savedRegion = regionRepository.save(region);
        assertNotNull(savedRegion.getId());
        assertEquals("Ile-de-France", savedRegion.getName());
    }

    @Test
    public void testFindById() {
        Region region = new Region();
        region.setName("Bavaria");
        region = regionRepository.save(region);

        Region found = regionRepository.findById(region.getId()).orElse(null);
        assertNotNull(found);
        assertEquals("Bavaria", found.getName());
    }
}
