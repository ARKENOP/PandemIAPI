package epsi.pandemia.pandemiapi.repository;

import epsi.pandemia.pandemiapi.entity.Continent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ContinentRepositoryTest {
    @Autowired
    private ContinentRepository continentRepository;

    @Test
    public void testSaveContinent() {
        Continent continent = new Continent();
        continent.setName("Europe");

        Continent savedContinent = continentRepository.save(continent);
        assertNotNull(savedContinent.getId());
        assertEquals("Europe", savedContinent.getName());
    }

    @Test
    public void testFindById() {
        Continent continent = new Continent();
        continent.setName("Asia");
        continent = continentRepository.save(continent);

        Continent found = continentRepository.findById(continent.getId()).orElse(null);
        assertNotNull(found);
        assertEquals("Asia", found.getName());
    }
}
