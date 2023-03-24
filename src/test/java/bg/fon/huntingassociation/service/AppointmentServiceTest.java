package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Appointment;
import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.domain.Venison;
import bg.fon.huntingassociation.mappers.AppointmentMapper;
import bg.fon.huntingassociation.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class AppointmentServiceTest {

    private AppointmentRepository appointmentRepository;
    private Team team;
    private Venison venison;
    private Appointment appointment;
    private AppointmentMapper appointmentMapper;

    @BeforeEach
    void setupService(AppointmentMapper appointmentMapper) {
        appointmentRepository = mock(AppointmentRepository.class);
        this.appointmentMapper = appointmentMapper;

        team = new Team();
        team.setId(1L);
        team.setName("Test Team");
        team.setMembers(2);

        venison = new Venison();
        venison.setId(1L);
        venison.setName("Test Venison");

        appointment = new Appointment();
        appointment.setId(1L);
        appointment.setDate(LocalDate.now());
        appointment.setComment("Test");
        appointment.setTeam(team);
        appointment.setVenison(venison);
    }


}