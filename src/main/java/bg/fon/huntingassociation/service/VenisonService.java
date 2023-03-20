package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Venison;
import bg.fon.huntingassociation.domain.dtos.VenisonDto;
import bg.fon.huntingassociation.exception.VenisonNotFoundException;
import bg.fon.huntingassociation.mappers.VenisonMapper;
import bg.fon.huntingassociation.repository.VenisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VenisonService {

    private final VenisonRepository venisonRepository;
    @Autowired
    VenisonMapper venisonMapper;

    @Autowired
    public VenisonService(VenisonRepository venisonRepository) {
        this.venisonRepository = venisonRepository;
    }

    public VenisonDto addVenison(Venison venison) {
        return venisonMapper.entityToDto(venisonRepository.save(venison));
    }

    public List<VenisonDto> findALlVenisons() {
        return venisonRepository.findAll()
                .stream()
                .map(venison -> venisonMapper.entityToDto(venison))
                .collect(Collectors.toList());
    }

    public VenisonDto findVenisonByIdDto(Long id) {
        Venison venison = venisonRepository.findById(id).orElseThrow(() -> new VenisonNotFoundException("Venison with id: " + id + " does not exist."));
        return  venisonMapper.entityToDto(venison);
    }

    public Venison findVenisonById(Long id){
        return venisonRepository.findById(id).get();
    }

    public void deleteVenison(Long id) {
        this.venisonRepository.deleteVenisonById(id);
    }


    public boolean chekDate(LocalDate date, Long venisonId) throws ValidationException {
        if (date == null) throw new ValidationException("Date must not be empty!");
        Venison venison = this.findVenisonById(venisonId);
        int day = LocalDate.parse(date.toString()).getDayOfYear();
        int fromDay = LocalDate.parse(venison.getFromDate().toString()).getDayOfYear();
        int toDay = LocalDate.parse(venison.getToDate().toString()).getDayOfYear();
        if (day >= fromDay) {
            if (day >= fromDay && day <= toDay) return true;
        } else {
            if (day <= fromDay && day >= toDay) return true;
        }
        return false;
    }

}
