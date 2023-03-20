package bg.fon.huntingassociation.service.manager;

import bg.fon.huntingassociation.domain.Appointment;
import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.domain.Venison;
import bg.fon.huntingassociation.service.AppointmentService;
import bg.fon.huntingassociation.service.TeamService;
import bg.fon.huntingassociation.service.VenisonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;


@Service
public class AppointmentManager {

    private final TeamService teamService;
    private final AppointmentService appointmentService;
    private final VenisonService venisonService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentManager.class);

    @Autowired
    public AppointmentManager(TeamService teamService, AppointmentService appointmentService, VenisonService venisonService) {
        this.teamService = teamService;
        this.appointmentService = appointmentService;
        this.venisonService = venisonService;
    }

    public Appointment makeAppointmentForTeam(Long teamId, Long venisonId) throws ValidationException {
//        venisonService.chekDate(date, venisonId);
        Team team = teamService.findTeamById(teamId);
        Venison venison = venisonService.findVenisonById(venisonId);
        if (team == null || venison == null)
            throw new ValidationException("In order to make an apoitment, you have to provide" +
                    "all the information!");
        Appointment appointment = new Appointment();
        appointment.setVenison(venison);
        appointment.setTeam(team);
        //ne treba da bude sadasnji datum
        // TODO: 3/11/2023  treba da se omoguci prosledjivanje validnog datuma
        appointment.setDate(LocalDate.now());
        appointmentService.addAppointment(appointment);
        LOGGER.info("Appointment has been successfully made for team " + team.getName());
        return appointment;
    }
}
