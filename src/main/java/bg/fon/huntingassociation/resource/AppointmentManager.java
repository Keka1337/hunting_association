package bg.fon.huntingassociation.resource;

import bg.fon.huntingassociation.domain.Appointment;
import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.domain.Venison;
import bg.fon.huntingassociation.exception.TeamNotFoundException;
import bg.fon.huntingassociation.exception.VenisonNotFoundException;
import bg.fon.huntingassociation.service.AppointmentService;
import bg.fon.huntingassociation.service.TeamService;
import bg.fon.huntingassociation.service.VenisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AppointmentManager {

    private final TeamService teamService;
    private final AppointmentService appointmentService;
    private final VenisonService venisonService;
    private static final Logger LOGGER = Logger.getLogger(AppointmentManager.class.getName());

    @Autowired
    public AppointmentManager(TeamService teamService, AppointmentService appointmentService, VenisonService venisonService) {
        this.teamService = teamService;
        this.appointmentService = appointmentService;
        this.venisonService = venisonService;
    }

    public Appointment makeAppointmentForTeam(Long teamId, Long venisonId, Date date) throws ValidationException {
            venisonService.chekDate(date,venisonId);
            Team team = teamService.findTeamById(teamId);
            Venison venison = venisonService.findVenisonById(venisonId);
            if(team == null || venison == null)
                throw new ValidationException("In order to make an apoitment, you have to provide" +
                        "all the information!");
            Appointment appointment = new Appointment();
            appointment.setTeam(team);
            appointment.setVenison(venison);
            appointment.setDate(date);
            appointmentService.addAppointment(appointment);
            LOGGER.log(Level.INFO, "Appointment has been successfully made for team " + team.getName());
            return appointment;
    }
}
