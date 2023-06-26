package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.constants.AppointmentStatus;
import bg.fon.huntingassociation.domain.Appointment;
import bg.fon.huntingassociation.domain.dtos.AppointmentDto;
import bg.fon.huntingassociation.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment createAppointment(Appointment appointment) throws ValidationException {
        if(checkIfAppointmentIsAlreadyMade(appointment.getTeam().getId(), appointment.getVenison().getId(), appointment.getDate())){
            throw new ValidationException("Appointment for team " + appointment.getTeam().getName() +
                    " has been already made! You cannot make two same appointments.");
        }
        appointment.setStatus(AppointmentStatus.APPROVED);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> findAllAppointments() {
        return this.appointmentRepository.findAll();
    }

    public Appointment updateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment findAppointmentById(Long id) {
        return appointmentRepository.findById(id).get();
    }

    public boolean checkIfAppointmentIsAlreadyMade(Long teamId, Long venisonId, LocalDate date) {
        Appointment appointment = appointmentRepository.checkIfAppointmentExists(teamId, venisonId, date);
        if (appointment == null)
            return false;
        return true;
    }

    public Long countAppointmentsForTeam(Long teamId) {
        return appointmentRepository.countByTeamId(teamId);
    }

    public Appointment cancelAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = findAppointmentById(appointmentDto.getId());
        if(AppointmentStatus.CANCELLED.equals(appointment.getStatus()))
            return appointment;
        appointment.setStatus(AppointmentStatus.CANCELLED);
        return updateAppointment(appointment);
    }

}
