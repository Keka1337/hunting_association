package bg.fon.huntingassociation.controller;

import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.domain.dtos.TeamDto;
import bg.fon.huntingassociation.service.TeamService;
import bg.fon.huntingassociation.service.manager.TeamManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/team")
public class TeamController {

    private final Logger LOG = LoggerFactory.getLogger(TeamController.class);
    private final TeamService teamService;
    private final TeamManager teamManager;

    public TeamController(TeamService teamService, TeamManager teamManager) {
        this.teamService = teamService;
        this.teamManager = teamManager;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTemas() {
        List<TeamDto> teams = this.teamService.findAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> finTeamById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(teamService.findTeamByIdDto(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        try {
            return new ResponseEntity<>(this.teamService.createTeam(team), HttpStatus.CREATED);
        } catch (ValidationException e) {
            LOG.error("Following error occured: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable("id") Long id) {
        this.teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/update-number-of-members/team/{teamId}/members/{members}")
    public ResponseEntity<?> updateNumberOfMembers(@PathVariable("teamId") Long teamId,
                                                   @PathVariable("members") Integer members){
        Team response = this.teamService.updateNumberOfMembers(teamId, members);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @PatchMapping("/remove/hunter/{hunterId}/team/{teamId}")
    public ResponseEntity<?> removeHunterFromTeam(@PathVariable("teamId") Long teamId,
                                                  @PathVariable("hunterId") Long hunterId){
        this.teamManager.removeHunterFromTeam(teamId, hunterId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/add/hunter/{hunterId}/team/{teamId}")
    public ResponseEntity<?> addHunterToTeam(@PathVariable("teamId") Long teamId,
                                                  @PathVariable("hunterId") Long hunterId){
        this.teamManager.addHunterToTeam(teamId, hunterId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
