package bg.fon.huntingassociation.controller;

import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.mappers.TeamMapper;
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
    private final TeamMapper teamMapper;

    public TeamController(TeamService teamService, TeamManager teamManager, TeamMapper teamMapper) {
        this.teamService = teamService;
        this.teamManager = teamManager;
        this.teamMapper = teamMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTemas() {
        List<Team> teams = this.teamService.findAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> finTeamById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(teamMapper.entityToDto(teamService.findTeamById(id)), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTeam(@RequestBody Team team) {
        try {
            return new ResponseEntity<>(teamMapper.entityToDto(teamService.createTeam(team)), HttpStatus.CREATED);
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
                                                   @PathVariable("members") Integer members) {
        return new ResponseEntity(teamMapper.entityToDto(teamService.updateNumberOfMembers(teamId, members)), HttpStatus.OK);
    }


    @PatchMapping("/remove/hunter/{hunterId}/team/{teamId}")
    public ResponseEntity<?> removeHunterFromTeam(@PathVariable("teamId") Long teamId,
                                                  @PathVariable("hunterId") Long hunterId) {
        this.teamManager.removeHunterFromTeam(teamId, hunterId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/add/hunter/{hunterId}/team/{teamId}")
    public ResponseEntity<?> addHunterToTeam(@PathVariable("teamId") Long teamId,
                                             @PathVariable("hunterId") Long hunterId) {
        this.teamManager.addHunterToTeam(teamId, hunterId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
