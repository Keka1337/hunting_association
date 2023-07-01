package bg.fon.huntingassociation.controller;

import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.mappers.TeamMapper;
import bg.fon.huntingassociation.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@Transactional
@RestController
@RequestMapping("api/v1/team")
public class TeamController {

    private final TeamService teamService;
    private final TeamMapper teamMapper;

    public TeamController(TeamService teamService,  TeamMapper teamMapper) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTemas() {
        List<Team> teams = this.teamService.findAllTeams();
        return new ResponseEntity<>(teams.stream().map(team -> teamMapper.entityToDto(team)), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findTeamById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(teamMapper.entityToDto(teamService.findTeamById(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTeam(@RequestBody Team team) {
        try {
            return new ResponseEntity<>(teamMapper.entityToDto(teamService.createTeam(team)), HttpStatus.CREATED);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/update/{id}/{name}")
    public ResponseEntity<?> updateTeamName(@PathVariable Long id, @PathVariable String name) {
        try {
            return new ResponseEntity<>(teamMapper.entityToDto(teamService.updateTeamName(id, name)), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable("id") Long id) {
        try {
            this.teamService.deleteTeam(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
