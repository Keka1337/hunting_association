package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Appointment;
import bg.fon.huntingassociation.domain.dtos.AppointmentDto;
import bg.fon.huntingassociation.exception.AppointmentNotFoundException;
import bg.fon.huntingassociation.mappers.AppointmentMapper;
import bg.fon.huntingassociation.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    @Autowired
    AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public AppointmentDto addAppointment(Appointment appointment) {
        return appointmentMapper.entityToDto(appointmentRepository.save(appointment));
    }

    public List<AppointmentDto> findAllAppointments() {
        return this.appointmentRepository.findAll()
                .stream()
                .map(appointment -> appointmentMapper.entityToDto(appointment))
                .collect(Collectors.toList());
    }

    public AppointmentDto updateAppointment(Appointment appointment) {
        return appointmentMapper.entityToDto(appointmentRepository.save(appointment));
    }

    public AppointmentDto findAppointmentByIdDto(Long id) {
       Appointment appointment = appointmentRepository.findById(id).orElseThrow(
                () -> new AppointmentNotFoundException("Appointment with id  " + id + " is not found."));
        return appointmentMapper.entityToDto(appointment);
    }

    public void deleteAppoitment(Long id) {
        this.appointmentRepository.deleteAppointmentById(id);
    }

    public List<AppointmentDto> findAppointmentsByTeamId(Long teamId) {
        List<Appointment> appointments = appointmentRepository.findALlByTeamId(teamId);
        return appointments.stream().map(appointment ->
                appointmentMapper.entityToDto(appointment)).collect(Collectors.toList());
    }
}
