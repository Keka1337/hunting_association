package bg.fon.huntingassociation.service.manager;

import bg.fon.huntingassociation.constants.AppointmentStatus;
import bg.fon.huntingassociation.domain.Appointment;
import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.domain.Venison;
import bg.fon.huntingassociation.domain.dtos.AppointmentDto;
import bg.fon.huntingassociation.mappers.AppointmentMapper;
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
    private final AppointmentMapper appointmentMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentManager.class);

    @Autowired
    public AppointmentManager(TeamService teamService, AppointmentService appointmentService,
                              VenisonService venisonService, AppointmentMapper appointmentMapper) {
        this.teamService = teamService;
        this.appointmentService = appointmentService;
        this.venisonService = venisonService;
        this.appointmentMapper = appointmentMapper;
    }

    public AppointmentDto makeAppointmentForTeam(Long teamId, Long venisonId, String date, String comment) throws ValidationException {
        Team team = teamService.findTeamById(teamId);
        Venison venison = venisonService.findVenisonById(venisonId);

        LocalDate convertedDate = venisonService.chekDate(date, venison);
        if (convertedDate == null)
            throw new ValidationException("Hunting period for " + venison.getName() + " is "
                    + venison.getFromDate() + " to " + venison.getToDate());

        if (appointmentService.checkIfAppointmentIsAlreadyMade(team.getId(), venison.getId(), convertedDate))
            throw new ValidationException("Appointment for team " + team.getName() +
                    " has been already made! You cannot make two same appointments.");
        Appointment appointment = new Appointment(venison, team, convertedDate, comment, AppointmentStatus.APPROVED);
        appointmentService.createAppointment(appointment);
        return appointmentMapper.entityToDto(appointment);
    }

    public Appointment cancelAppointment(Long id) {
        Appointment appointment = appointmentService.findAppointmentById(id);
        if(AppointmentStatus.CANCELLED.equals(appointment.getStatus()))
            return appointment;
        appointment.setStatus(AppointmentStatus.CANCELLED);
        return appointmentService.updateAppointment(appointment);
    }
}
