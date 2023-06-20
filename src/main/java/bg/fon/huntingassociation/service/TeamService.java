package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.exception.TeamNotFoundException;
import bg.fon.huntingassociation.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team createTeam(Team team) throws ValidationException {
        if (teamRepository.findByName(team.getName()) == null)
            return teamRepository.save(team);
        else
            throw new ValidationException("Team with name " + team.getName() + " already exist!");
    }

    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    public Team findTeamById(Long id) {
        return teamRepository.findById(id).orElseThrow(
                () -> new TeamNotFoundException("Team with id  " + id + " is not found."));
    }

    public void deleteTeam(Long id) {
        Team team = teamRepository.findById(id).get();
        team.getHunters().stream().forEach(t->t.setTeam(null));
        teamRepository.deleteById(id);
    }

    public Team updateTeam(Team team) {
        Team response = teamRepository.findById(team.getId()).get();
        response.setName(team.getName());
        response.setMembers(team.getMembers());
        return teamRepository.save(team);
    }

    public Team updateNumberOfMembers(Long teamId, Integer members) {
        Team team = teamRepository.findById(teamId).get();
        team.setMembers(team.getMembers()+members);
        return teamRepository.save(team);
    }

    public Team updateTeamName(Long id, String name) throws ValidationException {
        Team team = teamRepository.findById(id).get();
        if(teamRepository.findByName(name)!=null)
            throw new ValidationException("Team with name " + name + " already exist!");
        team.setName(name);
        return teamRepository.save(team);
    }

    public Team findByName(String name) {
        return this.teamRepository.findByName(name);
    }
}
