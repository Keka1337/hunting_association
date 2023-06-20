package bg.fon.huntingassociation.domain.dtos;

import bg.fon.huntingassociation.constants.AppointmentStatus;
import bg.fon.huntingassociation.domain.Venison;

import java.time.LocalDate;

public class AppointmentDto {
    private Long id;
    private LocalDate date;
    private VenisonDto venison;
    private TeamDto team;
    private String comment;
    private String status;

    public AppointmentDto(Long id, LocalDate date, VenisonDto venison, TeamDto team, String comment, String status) {
        this.id = id;
        this.date = date;
        this.venison = venison;
        this.team = team;
        this.comment = comment;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public VenisonDto getVenison() {
        return venison;
    }

    public void setVenison(VenisonDto venison) {
        this.venison = venison;
    }

    public TeamDto getTeam() {
        return team;
    }

    public void setTeam(TeamDto team) {
        this.team = team;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
