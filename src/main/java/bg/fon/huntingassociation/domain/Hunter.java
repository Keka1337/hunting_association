package bg.fon.huntingassociation.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Hunter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hunter_id", nullable = false)
    private Long id;
    private String name;
    private String lastname;
    private String jmbg;
    private String phone;
    @Embedded
    private Address address;

    @Column(name = "licence_number", updatable = true, unique = true)
    private String licenceNum;
    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;

    public Hunter() {

    }

    public Hunter(Long id, String name, String lastname, String jmbg, String phone, Address address, String licenceNum, Team team) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.jmbg = jmbg;
        this.phone = phone;
        this.address = address;
        this.licenceNum = licenceNum;
        this.team = team;
    }

    public Hunter(String name, String lastname, String jmbg, String phone, Address address, String licenceNum, Team team) {
        this.name = name;
        this.lastname = lastname;
        this.jmbg = jmbg;
        this.phone = phone;
        this.address = address;
        this.licenceNum = licenceNum;
        this.team = team;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLicenceNum() {
        return licenceNum;
    }

    public void setLicenceNum(String licenceNum) {
        this.licenceNum = licenceNum;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Hunter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", jmbg='" + jmbg + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                ", licenceNum='" + licenceNum + '\'' +
                ", team=" + team +
                '}';
    }
}
