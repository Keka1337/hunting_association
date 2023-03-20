package bg.fon.huntingassociation.domain.dtos;

import bg.fon.huntingassociation.domain.Venison;

import java.time.LocalDate;

public class AppointmentDto {
    private Long id;
    private LocalDate date;
    private String venisonName;
    private String teamName;
    private String comment;

    public AppointmentDto(Long id, LocalDate date, String venisonName, String teamName, String comment) {
        this.id = id;
        this.date = date;
        this.venisonName = venisonName;
        this.teamName = teamName;
        this.comment = comment;
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

    public String getVenisonName() {
        return venisonName;
    }

    public void setVenisonName(String venisonName) {
        this.venisonName = venisonName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
