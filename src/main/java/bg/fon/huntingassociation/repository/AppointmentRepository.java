package bg.fon.huntingassociation.repository;

import bg.fon.huntingassociation.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a WHERE a.team.id = :teamId AND a.venison.id = :venisonId AND a.date = :date " +
            "AND a.status = bg.fon.huntingassociation.constants.AppointmentStatus.APPROVED")
    Appointment checkIfAppointmentExists(@Param("teamId") Long teamId, @Param("venisonId") Long venisonId,
                                         @Param("date") LocalDate date);
    Long countByTeamId(@Param("teamId") Long teamId);
}
