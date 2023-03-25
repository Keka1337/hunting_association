package bg.fon.huntingassociation.repository;

import bg.fon.huntingassociation.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team,Long> {

    void deleteTeamById(Long id);

    Team findByName(String name);
}
