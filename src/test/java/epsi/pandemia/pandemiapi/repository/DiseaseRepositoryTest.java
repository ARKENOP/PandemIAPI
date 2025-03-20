package epsi.pandemia.pandemiapi.repository;

import epsi.pandemia.pandemiapi.entity.Disease;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class DiseaseRepositoryTest {
    @Autowired
    private DiseaseRepository diseaseRepository;

    @Test
    public void testSaveDisease() {
        Disease disease = new Disease();
        disease.setName("COVID-19");

        Disease savedDisease = diseaseRepository.save(disease);
        assertNotNull(savedDisease.getId());
        assertEquals("COVID-19", savedDisease.getName());
    }

    @Test
    public void testFindById() {
        Disease disease = new Disease();
        disease.setName("Influenza");
        disease = diseaseRepository.save(disease);

        Disease found = diseaseRepository.findById(disease.getId()).orElse(null);
        assertNotNull(found);
        assertEquals("Influenza", found.getName());
    }
}
