package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.repository.TeamRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.Optional;


@SpringBootTest
class TeamServiceTest {
    @InjectMocks
    private TeamService teamService;
    @Mock
    private TeamRepository teamRepository;

    @Test
    void createTeam_shouldPass() throws ValidationException {
        Team team = new Team();
        team.setId(7L);
        team.setName("Succ create team");
        team.setMembers(1);

        when(teamRepository.save(team)).thenReturn(team);
        assertEquals(team, teamService.createTeam(team));
    }

    @Test
    void createTeam_shouldThrowValidationException() throws ValidationException {
        Team team = new Team();
        team.setId(1L);
        team.setName("Fail create team");
        team.setMembers(7);

        when(teamRepository.findByName(team.getName())).thenReturn(team);
        assertThrows(ValidationException.class, () -> teamService.createTeam(team));
    }

    @Test
    void findAllTeams_shouldPass() {
        ArrayList<Team> teams = new ArrayList<>();
        Team team  = new Team();
        team.setId(2L);
        team.setName("Test team name");
        team.setMembers(3);
        teams.add(team);
        when(teamRepository.findAll()).thenReturn(teams);
        assertEquals(teams.size(), teamService.findAllTeams().size());
        assertEquals(teams, teamService.findAllTeams());
    }


    @Test
    void findTeamById_shouldPass() {
        Team team  = new Team();
        team.setId(12L);
        team.setName("Test find by id");
        team.setMembers(2);

        when(teamRepository.findById(12L)).thenReturn(Optional.of(team));
        assertEquals(team, teamService.findTeamById(12L));
    }

}