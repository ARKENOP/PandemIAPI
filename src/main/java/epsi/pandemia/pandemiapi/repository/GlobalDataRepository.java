package epsi.pandemia.pandemiapi.repository;

import epsi.pandemia.pandemiapi.entity.GlobalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlobalDataRepository extends JpaRepository<GlobalData, Long> {
}
