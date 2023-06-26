package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;


@SpringBootTest
class TeamServiceTest {

    private TeamService teamService;
    private TeamRepository teamRepository;
    private Team team;

    @BeforeEach
    void setupService() {
        teamService = mock(TeamService.class);
        teamRepository = mock(TeamRepository.class);
        team = new Team();
        team.setId(2L);
        team.setName("Test team name");
        team.setMembers(3);
    }

//    @Test
//    void createTeam_shouldPass() throws ValidationException {
//        when(teamRepository.findByName(team.getName())).thenReturn(null);
//        when(teamService.createTeam(team)).thenReturn(team);
//        assertEquals(team, teamService.createTeam(team));
//    }
//
//    @Test
//    void createTeam_shouldThrowValidationException() throws ValidationException {
//        when(teamRepository.findByName(team.getName())).thenReturn(team);
//        when(teamService.createTeam(team)).thenThrow(new ValidationException("Team with name " + team.getName() + " already exist!"));
//        assertThrows(ValidationException.class, () -> teamService.createTeam(team));
//    }
//
//    @Test
//    void findAllTeams_shouldPass() {
//        ArrayList<Team> teams = new ArrayList<>();
//        teams.add(team);
//        when(teamService.findAllTeams()).thenReturn(teams);
//        assertEquals(teams, teamService.findAllTeams());
//    }
//
//    @Test
//    void findAllTeams_shouldFail() {
//        ArrayList<Team> teams = new ArrayList<>();
//        teams.add(team);
//        when(teamService.findAllTeams()).thenReturn(teams);
//        assertNotEquals(null, teamService.findAllTeams());
//        assertNotEquals(new ArrayList<>(), teamService.findAllTeams());
//    }
//
//    @Test
//    void findTeamById_shouldPass() {
//        when(teamService.findTeamById(2L)).thenReturn(team);
//        assertEquals(team, teamService.findTeamById(2L));
//    }
//
//    @Test
//    void findTeamById_shouldFail() {
//        when(teamService.findTeamById(2L)).thenReturn(team);
//        assertNotEquals(null, teamService.findTeamById(2L));
//        assertNotEquals(new Team(), teamService.findTeamById(2L));
//    }
//
//    @Test
//    void findTeamById_shouldThrowException() {
//        when(teamService.findTeamById(2L)).thenThrow(new TeamNotFoundException("Team with id + " + 2 + " is not found."));
//        assertThrows(TeamNotFoundException.class, () -> teamService.findTeamById(2L));
//
//    }

//    @Test
//    void deleteTeam() {
//    }
//
//    @Test
//    void updateTeam() {
//    }
//
//    @Test
//    void updateNumberOfMembers() {
//    }
}