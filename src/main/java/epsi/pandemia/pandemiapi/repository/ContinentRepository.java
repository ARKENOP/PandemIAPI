package epsi.pandemia.pandemiapi.repository;

import epsi.pandemia.pandemiapi.entity.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinentRepository extends JpaRepository<Continent, Long> {
}
