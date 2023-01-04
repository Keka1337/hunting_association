package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Appointment;
import bg.fon.huntingassociation.exception.AppointmentNotFoundException;
import bg.fon.huntingassociation.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment addAppointment(Appointment appointment) {
        return this.appointmentRepository.save(appointment);
    }

    public List<Appointment> findAllAppointments() {
        return this.appointmentRepository.findAll();
    }

    public Appointment updateAppointment(Appointment appointment) {
        return this.appointmentRepository.save(appointment);
    }

    public Appointment findAppointmentById(Long id) {
        return appointmentRepository.findAppointmentById(id).orElseThrow(
                () -> new AppointmentNotFoundException("Appointment with id + " + id + " is not found."));
    }

    public void deleteAppoitment(Long id) {
        this.appointmentRepository.deleteAppointmentById(id);
    }

}
