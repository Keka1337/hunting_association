package bg.fon.huntingassociation.service.manager;

import bg.fon.huntingassociation.domain.Hunter;
import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.service.HunterService;
import bg.fon.huntingassociation.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamManager {

    private final TeamService teamService;
    private final HunterService hunterService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamManager.class);

    @Autowired
    public TeamManager(TeamService teamService, HunterService hunterService) {
        this.teamService = teamService;
        this.hunterService = hunterService;
    }

    public void removeHunterFromTeam(Long teamId, Long hunterId) {
        Team team = teamService.findTeamById(teamId);
        Hunter hunter = hunterService.findHunterById(hunterId);
        if (team.getHunters().contains(hunter)) {
            hunter.setTeam(null);
            team.setMembers(team.getMembers() - 1);
            teamService.updateTeam(team);
            hunterService.updateHunter(hunter);
        }
    }

    public void addHunterToTeam(Long teamId, Long hunterId) {
        Team team = teamService.findTeamById(teamId);
        Hunter hunter = hunterService.findHunterById(hunterId);
        hunter.setTeam(team);
        team.setMembers(team.getMembers() + 1);
        teamService.updateTeam(team);
        hunterService.updateHunter(hunter);

    }

}