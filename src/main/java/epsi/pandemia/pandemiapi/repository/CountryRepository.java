package epsi.pandemia.pandemiapi.repository;

import epsi.pandemia.pandemiapi.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
