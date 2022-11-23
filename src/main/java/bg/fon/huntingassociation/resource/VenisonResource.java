package bg.fon.huntingassociation.resource;

import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.domain.Venison;
import bg.fon.huntingassociation.service.VenisonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venison")
public class VenisonResource {

    private final VenisonService venisonService;

    public VenisonResource(VenisonService venisonService){
        this.venisonService = venisonService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Venison>> getAllVenisons() {
        List<Venison> venisons = this.venisonService.findALlVenisons();
        return new ResponseEntity<>(venisons, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Venison> finTeamById(@PathVariable("id") Long id) {
        Venison venison = this.venisonService.findVenisonById(id);
        return new ResponseEntity<>(venison, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Venison> addTeam(@RequestBody Venison venison) {
        Venison newVenison = this.venisonService.addVenison(venison);
        return new ResponseEntity<>(newVenison, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable("id") Long id) {
        this.venisonService.deleteVenison(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
