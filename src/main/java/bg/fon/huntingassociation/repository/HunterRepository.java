package bg.fon.huntingassociation.repository;

import bg.fon.huntingassociation.domain.Hunter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HunterRepository extends JpaRepository<Hunter, Long> {
    void deleteHunterById(Long id);

    Optional<Hunter> findHunterById(Long id); //queryMethod

    @Query("SELECT h FROM Hunter h WHERE h.jmbg =:jmbg")
    Hunter findByJmbg(@Param("jmbg") String jmbg);
}
