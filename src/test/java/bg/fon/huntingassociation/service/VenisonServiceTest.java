package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.constants.AppointmentStatus;
import bg.fon.huntingassociation.domain.Appointment;
import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.domain.Venison;
import bg.fon.huntingassociation.repository.VenisonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VenisonServiceTest {
    @InjectMocks
    private VenisonService venisonService;
    @Mock
    private VenisonRepository venisonRepository;

    @Test
    void createVenison_shouldPass(){
        Venison venison = new Venison(1L,"Fox", "Vulpes vulpes", "male",
                LocalDate.now(), LocalDate.now().plusDays(10), null);
        when(venisonRepository.save(venison)).thenReturn(venison);
        assertEquals(venison, venisonService.createVenison(venison));
    }
    @Test
    void  findALlVenisons_shouldPass(){
        Venison v1 = new Venison(11L,"Wolf", "Canis lupus", "male, female",
                LocalDate.now().plusDays(10), LocalDate.now().plusDays(100), null);
        Venison v2 = new Venison(12L,"Bear", "Ursus arctos", "male",
                LocalDate.now().plusDays(100), LocalDate.now().plusDays(200), null);

        List<Venison> list = new ArrayList<>();
        list.add(v1);
        list.add(v2);

        when(venisonRepository.findAll()).thenReturn(list);
        assertEquals(list, venisonService.findALlVenisons());
    }

    @Test
    void deleteVenison_shouldPass() throws ValidationException {
        Long id = 1L;
        Venison venison = new Venison();
        venison.setId(id);
        venison.setAppoitments(new ArrayList<>());

        when(venisonRepository.findById(anyLong())).thenReturn(Optional.of(venison));

        venisonService.deleteVenison(id);

        verify(venisonRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteVenison_shouldThrowValidationException() {
        Long id = 1L;
        Venison venison = new Venison();
        venison.setId(id);

        Team t1 = new Team(1L, "Test team", null, 0, null);
        List<Appointment> appointments = new ArrayList<>();
        Appointment a1 = new Appointment();
        a1.setVenison(venison);
        a1.setTeam(t1);
        a1.setStatus(AppointmentStatus.APPROVED);
        a1.setComment("");
        appointments.add(a1);

        venison.setAppoitments(appointments);

        when(venisonRepository.findById(1L)).thenReturn(Optional.of(venison));

        Assertions.assertThrows(ValidationException.class, () -> venisonService.deleteVenison(1L));
        verify(venisonRepository, never()).deleteById(id);
    }

    @Test
    void updateVenison_shouldPass() {
        Venison venison = new Venison();
        venison.setId(1L);

        when(venisonRepository.save(any(Venison.class))).thenReturn(venison);

        Venison updatedVenison = venisonService.updateVenison(venison);

        Assertions.assertEquals(venison.getId(), updatedVenison.getId());
        verify(venisonRepository, times(1)).save(venison);
    }

}