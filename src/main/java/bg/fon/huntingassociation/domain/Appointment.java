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

    public Appointment() {
    }

    public Appointment(Date date, Venison venison) {
        this.date = date;
        this.venison = venison;
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

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date=" + date +
                ", venison=" + venison +
                '}';
    }
}
