package epsi.pandemia.pandemiapi.repository;

import epsi.pandemia.pandemiapi.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
}
