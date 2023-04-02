package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Appointment;
import bg.fon.huntingassociation.domain.dtos.AppointmentDto;
import bg.fon.huntingassociation.exception.AppointmentNotFoundException;
import bg.fon.huntingassociation.mappers.AppointmentMapper;
import bg.fon.huntingassociation.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> findAllAppointments() {
        return this.appointmentRepository.findAll();
    }

    public Appointment updateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment findAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(
                () -> new AppointmentNotFoundException("Appointment with id  " + id + " is not found."));
    }

    public void deleteAppoitment(Long id) {
        this.appointmentRepository.deleteAppointmentById(id);
    }

    public List<Appointment> findAppointmentsByTeamId(Long teamId) {
        return appointmentRepository.findALlByTeamId(teamId);
    }

    public boolean checkIfAppointmentIsAlreadyMade(Long teamId, Long venisonId, LocalDate date) {
        Appointment appointment = appointmentRepository.checkIfAppointmentExists(teamId, venisonId, date);
        if (appointment == null)
            return false;
        return true;
    }

}
