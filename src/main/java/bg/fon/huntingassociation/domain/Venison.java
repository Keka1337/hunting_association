package bg.fon.huntingassociation.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Venison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venison_id")
    private Long id;
    private String name;
    @Column(name = "max_number")
    private int maxNum;
    @OneToMany(mappedBy = "venison")
    private List<Appointment> appointments;

    public Venison() {
    }

    public Venison(String name, int maxNum, List<Appointment> appointments) {
        this.name = name;
        this.maxNum = maxNum;
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

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public List<Appointment> getAppoitments() {
        return appointments;
    }

    public void setAppoitments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Venison{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxNum=" + maxNum +
                ", appointments=" + appointments +
                '}';
    }
}
