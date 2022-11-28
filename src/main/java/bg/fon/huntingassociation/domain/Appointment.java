package bg.fon.huntingassociation.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appointment_id")
    private Long id;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "venison_id")
    private Venison venison;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Appointment() {
    }

    public Appointment(Date date, Venison venison, Team team) {
        this.date = date;
        this.venison = venison;
        this.team = team;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date=" + date +
                ", venison=" + venison +
                ", team=" + team +
                '}';
    }
}
