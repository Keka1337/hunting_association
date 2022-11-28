package bg.fon.huntingassociation.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "team")
    private List<Hunter> hunters;
    private int members;

    @OneToMany(mappedBy = "team")
    private List<Appointment> appointments;

    public Team(String name, List<Hunter> hunters, int members, List<Appointment> appointments) {
        this.name = name;
        this.hunters = hunters;
        this.members = members;
        this.appointments = appointments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Hunter> getHunters() {
        return hunters;
    }

    public void setHunters(List<Hunter> hunters) {
        this.hunters = hunters;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hunters=" + hunters +
                ", members=" + members +
                ", appointments=" + appointments +
                '}';
    }
}
