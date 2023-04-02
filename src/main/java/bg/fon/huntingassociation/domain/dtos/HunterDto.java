package bg.fon.huntingassociation.domain.dtos;

public class HunterDto {
    private Long id;
    private String name;
    private String lastname;
    private String jmbg;
    private TeamDto team;
    private String phone;
    private String licenceNum;
    private String street;
    private String city;
    private String postalCode;

    public HunterDto(Long id, String name, String lastname, String jmbg, TeamDto team, String phone, String licenceNum, String street, String city, String postalCode) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.jmbg = jmbg;
        this.team = team;
        this.phone = phone;
        this.licenceNum = licenceNum;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
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

    public TeamDto getTeam() {
        return team;
    }

    public void setTeam(TeamDto team) {
        this.team = team;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
