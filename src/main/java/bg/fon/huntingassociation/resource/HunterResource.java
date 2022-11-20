package bg.fon.huntingassociation.resource;

import bg.fon.huntingassociation.domain.Hunter;
import bg.fon.huntingassociation.service.HunterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hunter") //base url
public class HunterResource {

    private final HunterService hunterService;

    public HunterResource(HunterService hunterService) {
        this.hunterService = hunterService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hunter>> getAllHunters() {
        List<Hunter> hunters = this.hunterService.findAllHunters();
        return new ResponseEntity<>(hunters, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Hunter> getHunterById(@PathVariable("id") Long id) {
        Hunter hunter = this.hunterService.findHunterById(id);
        return new ResponseEntity<>(hunter, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Hunter> addHunter(@RequestBody Hunter hunter) {
        Hunter newHunter = this.hunterService.addHunter(hunter);
        return new ResponseEntity<>(newHunter, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Hunter> updateHunter(@RequestBody Hunter hunter) {
        Hunter updatedHunter = this.hunterService.updateHunter(hunter);
        return new ResponseEntity<>(updatedHunter, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHunter(@PathVariable("id") Long id) {
        this.hunterService.deleteHunter(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
