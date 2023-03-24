package bg.fon.huntingassociation.controller;

import bg.fon.huntingassociation.domain.Venison;
import bg.fon.huntingassociation.mappers.VenisonMapper;
import bg.fon.huntingassociation.service.VenisonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@Transactional
@RestController
@RequestMapping("/venison")
public class VenisonController {

    private final VenisonService venisonService;
    private final VenisonMapper venisonMapper;


    public VenisonController(VenisonService venisonService, VenisonMapper venisonMapper) {
        this.venisonService = venisonService;
        this.venisonMapper = venisonMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllVenisons() {
        return new ResponseEntity<>(venisonService.findALlVenisons().stream().map(venison -> venisonMapper.entityToDto(venison)), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findVenisonById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(venisonMapper.entityToDto(venisonService.findVenisonById(id)), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addVenison(@RequestBody Venison venison) {
        return new ResponseEntity<>(venisonMapper.entityToDto(venisonService.addVenison(venison)), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVenison(@PathVariable("id") Long id) {
        this.venisonService.deleteVenison(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
