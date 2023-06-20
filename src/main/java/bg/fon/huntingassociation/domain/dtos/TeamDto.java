package bg.fon.huntingassociation.domain.dtos;

public class TeamDto {
    private Long id;
    private String name;
    private int members;

    public TeamDto(Long id, String name, int members) {
        this.id = id;
        this.name = name;
        this.members = members;
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

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }
}
