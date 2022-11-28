package bg.fon.huntingassociation.repository;

import bg.fon.huntingassociation.domain.Hunter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HunterRepository extends JpaRepository<Hunter, Long> {
    void deleteHunterById(Long id);

    Optional<Hunter> findHunterById(Long id); //queryMethod
}
