package bg.fon.huntingassociation.controller;

import bg.fon.huntingassociation.domain.Hunter;
import bg.fon.huntingassociation.mappers.HunterMapper;
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
    private final HunterMapper hunterMapper;
    Logger LOG = LoggerFactory.getLogger(HunterController.class);

    public HunterController(HunterService hunterService, HunterMapper hunterMapper) {
        this.hunterService = hunterService;
        this.hunterMapper = hunterMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllHunters() {
        List<Hunter> hunters = hunterService.findAllHunters();
        return new ResponseEntity<>(hunters.stream().map(hunter -> hunterMapper.entityToDto(hunter)), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getHunterById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(hunterMapper.entityToDto(hunterService.findHunterById(id)), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addHunter(@RequestBody Hunter hunter) {
        try {
            return new ResponseEntity<>(hunterMapper.entityToDto(hunterService.addHunter(hunter)), HttpStatus.CREATED);
        } catch (ValidationException e) {
            LOG.error("Following error occured: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateHunter(@RequestBody Hunter hunter) {
        return new ResponseEntity<>(hunterMapper.entityToDto(hunterService.updateHunter(hunter)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHunter(@PathVariable("id") Long id) {
        hunterService.deleteHunter(id);
        return ResponseEntity.ok("Hunter has been successfully removed!");
    }
}
