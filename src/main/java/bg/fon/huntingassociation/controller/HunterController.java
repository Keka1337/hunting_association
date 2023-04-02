package bg.fon.huntingassociation.controller;

import bg.fon.huntingassociation.domain.Hunter;
import bg.fon.huntingassociation.domain.dtos.HunterDto;
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
        try {
            return new ResponseEntity<>(hunterMapper.entityToDto(hunterService.findHunterById(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find-all-for-team/{teamId}")
    public ResponseEntity<?> getHunterByTeamId(@PathVariable("teamId") Long teamId) {
        try {
            return new ResponseEntity<>(hunterService.findHunterByTeamId(teamId).stream().map(hunter -> hunterMapper.entityToDto(hunter)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addHunterDto(@RequestBody HunterDto hunterDto) {
        try {
            return new ResponseEntity<>(hunterMapper.entityToDto(hunterService.addHunterDto(hunterDto)), HttpStatus.CREATED);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateHunter(@RequestBody HunterDto hunter) {
        try {
            return new ResponseEntity<>(hunterMapper.entityToDto(hunterService.updateHunterDto(hunter)), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHunter(@PathVariable("id") Long id) {
        try {
            hunterService.deleteHunter(id);
            return ResponseEntity.ok("Hunter has been successfully removed!");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
