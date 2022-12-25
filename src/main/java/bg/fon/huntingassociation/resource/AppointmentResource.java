package bg.fon.huntingassociation.resource;

import bg.fon.huntingassociation.domain.Appointment;
import bg.fon.huntingassociation.domain.Hunter;
import bg.fon.huntingassociation.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentResource {

    private final AppointmentService appointmentService;
    private final AppointmentManager appointmentManager;

    public AppointmentResource(AppointmentService appointmentService, AppointmentManager appointmentManager) {
        this.appointmentService = appointmentService;
        this.appointmentManager = appointmentManager;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = this.appointmentService.findAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") Long id) {
        Appointment appointment = this.appointmentService.findAppointmentById(id);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Appointment> addHunter(@RequestBody Appointment appointment) {
        Appointment newAppointment = this.appointmentService.addAppointment(appointment);
        return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment) {
        Appointment updatedAppointment = this.appointmentService.updateAppointment(appointment);
        return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable("id") Long id) {
        this.appointmentService.deleteAppoitment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/makeForTeam/{teamId}/venison/{venisonId}/{date}")
    public ResponseEntity<?> makeAppointmentFroTeam(@PathVariable("teamId") Long teamId,
                                                    @PathVariable("venisonId") Long venisonId,
                                                    @PathVariable("date") Date date) {
        try {
            Appointment appointment = appointmentManager.makeAppointmentForTeam(teamId,venisonId,date);
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        }catch (ValidationException e){
            return new ResponseEntity<>("DATE IS NOT PROVIDED! " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}