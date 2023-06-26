package bg.fon.huntingassociation.controller;

import bg.fon.huntingassociation.domain.Appointment;
import bg.fon.huntingassociation.domain.dtos.AppointmentDto;
import bg.fon.huntingassociation.mappers.AppointmentMapper;
import bg.fon.huntingassociation.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;


@Transactional
@RestController
@RequestMapping("api/v1/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentMapper appointmentMapper;

    public AppointmentController(AppointmentService appointmentService,  AppointmentMapper appointmentMapper) {
        this.appointmentService = appointmentService;
        this.appointmentMapper = appointmentMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAppointments() {
        return new ResponseEntity<>(appointmentService.findAllAppointments()
                .stream()
                .map(appointment -> appointmentMapper.entityToDto(appointment)),
                HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(appointmentMapper.entityToDto(appointmentService.findAppointmentById(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAppointment(@RequestBody Appointment appointment) {
        try {
            return new ResponseEntity<>(appointmentMapper.entityToDto(appointmentService.createAppointment(appointment)), HttpStatus.CREATED);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/count-appointments-for-team/{teamId}")
    public ResponseEntity<?> countAppointmentsForTeam(@PathVariable("teamId") Long teamId) {
        try {
            return new ResponseEntity<>(appointmentService.countAppointmentsForTeam(teamId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/cancel")
    public ResponseEntity<?> cancelAppointment(@RequestBody AppointmentDto appointmentDto) {
        try {
            return new ResponseEntity<>(appointmentMapper.entityToDto(appointmentService.cancelAppointment(appointmentDto)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}