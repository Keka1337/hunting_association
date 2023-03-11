package bg.fon.huntingassociation.resource;

import bg.fon.huntingassociation.domain.Hunter;
import bg.fon.huntingassociation.service.HunterService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/hunter") //base url
public class HunterResource {

    private final HunterService hunterService;
    Logger LOG = LoggerFactory.getLogger(HunterResource.class);

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
        try {
            return new ResponseEntity<>(this.hunterService.addHunter(hunter), HttpStatus.CREATED);
        } catch (ValidationException e) {
            LOG.error("Following error occured: " + e.getMessage());
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
