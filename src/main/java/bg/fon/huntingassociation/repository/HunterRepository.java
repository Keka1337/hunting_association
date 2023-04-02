package bg.fon.huntingassociation.repository;

import bg.fon.huntingassociation.domain.Hunter;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.ArrayList;
import java.util.Optional;

public interface HunterRepository extends JpaRepository<Hunter, Long> {

    Hunter findByJmbg(String jmbg);

    Hunter findByLicenceNum(String licenceNum);

    ArrayList<Hunter> findAllByTeamId(Long teamId);
}
