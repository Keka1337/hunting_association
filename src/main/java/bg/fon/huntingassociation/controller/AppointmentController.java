package bg.fon.huntingassociation.controller;

import bg.fon.huntingassociation.domain.Appointment;
import bg.fon.huntingassociation.domain.dtos.AppointmentDto;
import bg.fon.huntingassociation.mappers.AppointmentMapper;
import bg.fon.huntingassociation.service.AppointmentService;
import bg.fon.huntingassociation.service.manager.AppointmentManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


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
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateAppointment(@RequestBody AppointmentDto appointmentDto) {
        try {
            return new ResponseEntity<>(appointmentMapper.entityToDto(appointmentManager.updateAppointmentDto(appointmentDto)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable("id") Long id) {
        try {
            this.appointmentService.deleteAppoitment(id);
            return ResponseEntity.ok("Appointment has been successfully removed!");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/make/team/{teamName}/venison/{venisonName}/date/{date}")
    public ResponseEntity<?> makeAppointmentForTeam(@PathVariable("teamName") String teamName,
                                                    @PathVariable("venisonName") String venisonName,
                                                    @PathVariable("date") String date,
                                                    @RequestParam(defaultValue = "") String comment) {
        try {
            return new ResponseEntity<>(appointmentManager.makeAppointmentForTeam(teamName, venisonName, date, comment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/cancel")
    public ResponseEntity<?> cancelAppointment(@RequestBody AppointmentDto appointmentDto) {
        try {
            return new ResponseEntity<>(appointmentMapper.entityToDto(appointmentManager.cancelAppointment(appointmentDto)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByTeamId/{teamId}")
    public ResponseEntity<?> getAppointmentsByTeamId(@PathVariable("teamId") Long teamId) {
        try {
            return new ResponseEntity<>(appointmentService.findAppointmentsByTeamId(teamId)
                    .stream()
                    .map(appointment -> appointmentMapper.entityToDto(appointment)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}