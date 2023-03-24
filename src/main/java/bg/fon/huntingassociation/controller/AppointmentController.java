package bg.fon.huntingassociation.controller;

import bg.fon.huntingassociation.domain.Appointment;
import bg.fon.huntingassociation.mappers.AppointmentMapper;
import bg.fon.huntingassociation.service.AppointmentService;
import bg.fon.huntingassociation.service.manager.AppointmentManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@Transactional
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentManager appointmentManager;
    private final AppointmentMapper appointmentMapper;

    public AppointmentController(AppointmentService appointmentService, AppointmentManager appointmentManager, AppointmentMapper appointmentMapper) {
        this.appointmentService = appointmentService;
        this.appointmentManager = appointmentManager;
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
        return new ResponseEntity<>(appointmentMapper.entityToDto(appointmentService.findAppointmentById(id)), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAppointment(@RequestBody Appointment appointment) {
        return new ResponseEntity<>(appointmentMapper.entityToDto(appointmentService.addAppointment(appointment)), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointment) {
        return new ResponseEntity<>(appointmentMapper.entityToDto(appointmentService.updateAppointment(appointment)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable("id") Long id) {
        this.appointmentService.deleteAppoitment(id);
        return ResponseEntity.ok("Appointment has been successfully removed!");
    }

    @PostMapping("/make/team/{teamId}/venison/{venisonId}")
    public ResponseEntity<?> makeAppointmentForTeam(@PathVariable("teamId") Long teamId,
                                                    @PathVariable("venisonId") Long venisonId) {
        try {
            Appointment appointment = appointmentManager.makeAppointmentForTeam(teamId, venisonId);
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>("DATE IS NOT PROVIDED! " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByTeamId/{teamId}")
    public ResponseEntity<?> getAppointmentByTeamId(@PathVariable("teamId") Long teamId) {
        try {
            return new ResponseEntity<>(appointmentService.findAppointmentsByTeamId(teamId)
                    .stream()
                    .map(appointment -> appointmentMapper.entityToDto(appointment)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}