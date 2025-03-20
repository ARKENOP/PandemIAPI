package epsi.pandemia.pandemiapi.repository;

import epsi.pandemia.pandemiapi.entity.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CountryRepositoryTest {
    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void testSaveCountry() {
        Country country = new Country();
        country.setName("France");
        country.setIso3("FR");

        Country savedCountry = countryRepository.save(country);
        assertNotNull(savedCountry.getId());
        assertEquals("France", savedCountry.getName());
    }

    @Test
    public void testFindById() {
        Country country = new Country();
        country.setName("USA");
        country = countryRepository.save(country);

        Country found = countryRepository.findById(country.getId()).orElse(null);
        assertNotNull(found);
        assertEquals("USA", found.getName());
    }
}
