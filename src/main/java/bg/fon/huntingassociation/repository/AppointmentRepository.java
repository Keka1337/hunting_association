package bg.fon.huntingassociation.repository;

import bg.fon.huntingassociation.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    void deleteAppointmentById(Long id);

    Optional<Appointment> findAppointmentById(Long aLong);

}
