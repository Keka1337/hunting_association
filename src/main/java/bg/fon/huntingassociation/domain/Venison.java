package bg.fon.huntingassociation.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Venison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venison_id")
    private Long id;
    private String name;
    @Column(name = "scientific_name", nullable = true)
    private String latinName;
    private String gender;
    //starting snd ending date of hunting season
    private LocalDate fromDate;
    private LocalDate toDate;
    @Column(name = "max_number")
    private int maxNum;
    @OneToMany(mappedBy = "venison")
    private List<Appointment> appointments;

    public Venison() {
    }

    public Venison(String name, String latinName, String gender, LocalDate fromDate, LocalDate toDate, int maxNum) {
        this.name = name;
        this.latinName = latinName;
        this.gender = gender;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.maxNum = maxNum;
    }

    public Venison(String name, String gender, LocalDate fromDate, LocalDate toDate, int maxNum) {
        this.name = name;
        this.gender = gender;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.maxNum = maxNum;
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

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "Venison{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latinName='" + latinName + '\'' +
                ", gender='" + gender + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", maxNum=" + maxNum +
                ", appointments=" + appointments +
                '}';
    }
}
