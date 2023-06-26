package bg.fon.huntingassociation.domain;

import bg.fon.huntingassociation.constants.AppointmentStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "venison_id")
    private Venison venison;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private String comment;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    public Appointment() {
    }

    public Appointment(Venison venison, Team team, LocalDate date, String comment, AppointmentStatus status) {
        this.date = date;
        this.venison = venison;
        this.team = team;
        this.comment = comment;
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Venison getVenison() {
        return venison;
    }

    public void setVenison(Venison venison) {
        this.venison = venison;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date=" + date +
                ", venison=" + venison +
                ", team=" + team +
                ", comment='" + comment + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
