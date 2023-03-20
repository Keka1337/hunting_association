package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.domain.dtos.TeamDto;
import bg.fon.huntingassociation.exception.TeamNotFoundException;
import bg.fon.huntingassociation.mappers.TeamMapper;
import bg.fon.huntingassociation.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team createTeam(Team team) throws ValidationException {
        if (teamRepository.findTeamByName(team.getName()) == null)
            return teamRepository.save(team);
        else
            throw new ValidationException("Team with name " + team.getName() + " already exist!");
    }

    public List<TeamDto> findAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return  teams
                .stream()
                .map(team -> teamMapper.entityToDto(team))
                .collect(Collectors.toList());
    }

    public Team findTeamById(Long id) {
        return teamRepository.findTeamById(id).orElseThrow(
                () -> new TeamNotFoundException("Team with id + " + id + " is not found."));
    }

    public TeamDto findTeamByIdDto(Long id) {
        Team team = teamRepository.findTeamById(id).orElseThrow(
                () -> new TeamNotFoundException("Team with id + " + id + " is not found."));
        return teamMapper.entityToDto(team);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteTeamById(id);
    }

    public Team updateTeam(Team team) {
        Team response = teamRepository.findTeamById(team.getId()).get();
        response.setName(team.getName());
        response.setMembers(team.getMembers());
        return teamRepository.save(team);
    }

    public Team updateNumberOfMembers(Long teamId, Integer members) {
        Team team = teamRepository.findTeamById(teamId).get();
        if (members != null)
            team.setMembers(members);
        return teamRepository.save(team);
    }
}
