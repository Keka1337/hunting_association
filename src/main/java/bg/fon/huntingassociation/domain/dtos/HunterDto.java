package bg.fon.huntingassociation.domain.dtos;

public class HunterDto {
    private Long id;
    private String name;
    private String lastname;
    private String jmbg;
    private TeamDto team;

    public HunterDto(Long id, String name, String lastname, String jmbg, TeamDto team) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.jmbg = jmbg;
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

    public TeamDto getTeam() {
        return team;
    }

    public void setTeam(TeamDto team) {
        this.team = team;
    }
}
