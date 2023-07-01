package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Address;
import bg.fon.huntingassociation.domain.Hunter;
import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.repository.HunterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HunterServiceTest {

    @Mock
    private HunterRepository hunterRepository;

    @Mock
    private TeamService teamService;

    @InjectMocks
    private HunterService hunterService;

    @Test
    void findAllHunters_shoudlPass() {
        Hunter h1 = new Hunter("Adam","Markovic","2512978454215","381641215147",
                new Address("Omladinskih Brigada", "Beograd", "11000"), "14A", null);
        Hunter h2 = new Hunter("Luka","Mordric","2512978454222","381641215222",
                new Address("Slobodana Penezica", "Barajevo", "11460"), "15A", null);
        List<Hunter> list = new ArrayList<>();
        list.add(h1);
        list.add(h2);

        when(hunterRepository.findAll()).thenReturn(list);

        assertEquals(list, hunterService.findAllHunters());
    }

    @Test
    void findHunterById_shoudlPass() {
        Hunter hunter = new Hunter(1L, "Mladen","Simic","2411978454111","381641115147",
                new Address("Kozaracka", "Pirot", "18000"), "1L", null);
        when(hunterRepository.findById(anyLong())).thenReturn(Optional.of(hunter));
        Hunter foundHunter = hunterService.findHunterById(1L);
        Assertions.assertEquals(hunter, foundHunter);
    }

    @Test
    void deleteHunter_shouldPassAndDeleteHunterAndUpdateTeamMembers() {
        Long hunterId = 1L;
        Hunter hunter = new Hunter(1L, "Mladen","Simic","2411978454111","381641115147",
                new Address("Kozaracka", "Pirot", "18000"), "1L", null);
        Team team = new Team();
        team.setId(1L);
        hunter.setTeam(team);

        when(hunterRepository.findById(anyLong())).thenReturn(Optional.of(hunter));

        hunterService.deleteHunter(hunterId);

        verify(hunterRepository, times(1)).deleteById(hunterId);
        verify(teamService, times(1)).updateNumberOfMembers(team.getId(), -1);
    }

    @Test
    void findHunterByTeamId_shouldPass() {
        Long teamId = 1L;
        Hunter h1 = new Hunter("Adam","Markovic","2512978454215","381641215147",
                new Address("Omladinskih Brigada", "Beograd", "11000"), "14A", null);
        Hunter h2 = new Hunter("Luka","Mordric","2512978454222","381641215222",
                new Address("Slobodana Penezica", "Barajevo", "11460"), "15A", null);
        List<Hunter> hunters = new ArrayList<>();
        hunters.add(h1);
        hunters.add(h2);

        when(hunterRepository.findAllByTeamId(anyLong())).thenReturn((ArrayList<Hunter>) hunters);

        ArrayList<Hunter> foundHunters = hunterService.findHunterByTeamId(teamId);

        Assertions.assertEquals(hunters, foundHunters);
    }
}
