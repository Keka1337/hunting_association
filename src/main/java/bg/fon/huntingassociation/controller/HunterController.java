package bg.fon.huntingassociation.controller;

import bg.fon.huntingassociation.domain.Hunter;
import bg.fon.huntingassociation.domain.dtos.HunterDto;
import bg.fon.huntingassociation.service.HunterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/hunter") //base url
public class HunterController {

    private final HunterService hunterService;
    Logger LOG = LoggerFactory.getLogger(HunterController.class);

    public HunterController(HunterService hunterService) {
        this.hunterService = hunterService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllHunters() {
        List<HunterDto> hunters = hunterService.findAllHunters();
        return new ResponseEntity<>(hunters, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Hunter> getHunterById(@PathVariable("id") Long id) {
        Hunter hunter = hunterService.findHunterByIdDto(id);
        return new ResponseEntity<>(hunter, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Hunter> addHunter(@RequestBody Hunter hunter) {
        try {
            return new ResponseEntity<>(hunterService.addHunter(hunter), HttpStatus.CREATED);
        } catch (ValidationException e) {
            LOG.error("Following error occured: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Hunter> updateHunter(@RequestBody Hunter hunter) {
        Hunter updatedHunter = this.hunterService.updateHunter(hunter);
        return new ResponseEntity<>(updatedHunter, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHunter(@PathVariable("id") Long id) {
        hunterService.deleteHunter(id);
        return ResponseEntity.ok("Hunter has been successfully removed!");
    }
}
