package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.constants.AppointmentStatus;
import bg.fon.huntingassociation.domain.Appointment;
import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.domain.Venison;
import bg.fon.huntingassociation.domain.dtos.AppointmentDto;
import bg.fon.huntingassociation.repository.AppointmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    void createAppointment_shouldPass() throws ValidationException {
        Appointment appointment = new Appointment();
        appointment.setTeam(new Team(1L, "Test team", null, 0, null));
        appointment.setVenison(new Venison(1L,"Fox", "Vulpes vulpes", "male",
                LocalDate.now(), LocalDate.now().plusDays(10), null));
        appointment.setDate(LocalDate.now());
        appointment.setStatus(AppointmentStatus.APPROVED);

        when(appointmentRepository.checkIfAppointmentExists(anyLong(), anyLong(), any(LocalDate.class))).thenReturn(null);
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        Appointment createdAppointment = appointmentService.createAppointment(appointment);

        Assertions.assertEquals(AppointmentStatus.APPROVED, createdAppointment.getStatus());
        verify(appointmentRepository, times(1)).save(appointment);
    }

    @Test
    void createAppointment_shouldThrowValidationException() {
        Appointment appointment = new Appointment();
        appointment.setTeam(new Team(11L, "Exists team", null, 0, null));
        appointment.setVenison(new Venison(11L,"Wolf", "Canis lupus", "male, female",
                LocalDate.now().plusDays(10), LocalDate.now().plusDays(100), null));
        appointment.setDate(LocalDate.now());
        appointment.setStatus(AppointmentStatus.APPROVED);
        appointment.setComment("shouldThrowValidationException");

        when(appointmentRepository.checkIfAppointmentExists(anyLong(), anyLong(), any(LocalDate.class))).thenReturn(appointment);

        Assertions.assertThrows(ValidationException.class, () -> appointmentService.createAppointment(appointment));
        verify(appointmentRepository, never()).save(appointment);
    }

    @Test
    void findAllAppointments_ShouldPass() {
        Venison v1 = new Venison(1L,"Fox", "Vulpes vulpes", "male",
                LocalDate.now(), LocalDate.now().plusDays(10), null);
        Team t1 = new Team(1L, "Test team", null, 0, null);
        Appointment a1 = new Appointment();
        a1.setVenison(v1);
        a1.setTeam(t1);
        a1.setStatus(AppointmentStatus.APPROVED);
        a1.setComment("");

        List<Appointment> list = new ArrayList<>();
        list.add(a1);

        when(appointmentRepository.findAll()).thenReturn(list);

        Assertions.assertEquals(list, appointmentService.findAllAppointments());
    }

    @Test
    void updateAppointment_shouldPass() {
        Appointment appointment = new Appointment();
        appointment.setId(10L);

        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        Appointment updatedAppointment = appointmentService.updateAppointment(appointment);

        Assertions.assertEquals(appointment.getId(), updatedAppointment.getId());
        verify(appointmentRepository, times(1)).save(appointment);
    }

    @Test
    void findAppointmentById_shouldPass() {
        Appointment appointment = new Appointment();
        appointment.setId(111L);

        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointment));

        Appointment foundAppointment = appointmentService.findAppointmentById(111L);

        Assertions.assertEquals(appointment, foundAppointment);
    }

    @Test
    void checkIfAppointmentIsAlreadyMade_shouldReturnTrue() {
        when(appointmentRepository.checkIfAppointmentExists(anyLong(), anyLong(), any(LocalDate.class))).thenReturn(new Appointment());
        boolean isAppointmentMade = appointmentService.checkIfAppointmentIsAlreadyMade(1L, 1L, LocalDate.now());

        Assertions.assertTrue(isAppointmentMade);
    }

    @Test
    void countAppointmentsForTeam_ValidTeamId_ReturnsCount() {
        Long teamId = 1L;
        Long appointmentCount = 5L;

        when(appointmentRepository.countByTeamId(anyLong())).thenReturn(appointmentCount);

        Long result = appointmentService.countAppointmentsForTeam(teamId);

        Assertions.assertEquals(appointmentCount, result);
    }

    @Test
    void cancelAppointment_appointmentAlreadyCancelled_shouldReturnSameAppointment() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(1L);

        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setStatus(AppointmentStatus.CANCELLED);

        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointment));

        Appointment result = appointmentService.cancelAppointment(appointmentDto);

        Assertions.assertEquals(appointment, result);
        verify(appointmentRepository, never()).save(appointment);
    }

    @Test
    void cancelAppointment_AppointmentNotCancelled_shouldUpdateAppointment() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(1L);

        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setStatus(AppointmentStatus.APPROVED);

        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointment));
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        Appointment result = appointmentService.cancelAppointment(appointmentDto);

        Assertions.assertEquals(AppointmentStatus.CANCELLED, result.getStatus());
        verify(appointmentRepository, times(1)).save(appointment);
    }
}