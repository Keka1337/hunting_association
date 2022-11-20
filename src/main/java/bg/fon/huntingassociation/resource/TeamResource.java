package bg.fon.huntingassociation.resource;

import bg.fon.huntingassociation.domain.Hunter;
import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamResource {

    private final TeamService teamService;

    public TeamResource(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Team>> getAllTemas() {
        List<Team> teams = this.teamService.findAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Team> finTeamById(@PathVariable("id") Long id) {
        Team team = this.teamService.findTeamById(id);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        Team newTeam = this.teamService.createTeam(team);
        return new ResponseEntity<>(newTeam, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable("id") Long id) {
        this.teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
