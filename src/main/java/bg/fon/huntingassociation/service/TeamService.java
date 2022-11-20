package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.exception.TeamNotFoundException;
import bg.fon.huntingassociation.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public Team createTeam(Team team){
        return teamRepository.save(team);
    }

    public List<Team> findAllTeams(){
        return teamRepository.findAll();
    }

    public Team findTeamById(Long id){
        return teamRepository.findTeamById(id).orElseThrow(
                ()-> new TeamNotFoundException("Team with id + " + id + " is not found."));
    }

    public void deleteTeam(Long id){
        teamRepository.deleteTeamById(id);
    }
}
