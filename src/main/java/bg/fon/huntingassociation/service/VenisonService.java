package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Venison;
import bg.fon.huntingassociation.repository.VenisonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.List;

@Service
public class VenisonService {

    private final VenisonRepository venisonRepository;

    private final Logger LOG = LoggerFactory.getLogger(VenisonService.class);

    @Autowired
    public VenisonService(VenisonRepository venisonRepository) {
        this.venisonRepository = venisonRepository;
    }

    public List<Venison> findALlVenisons() {
        return venisonRepository.findAll();
    }

    public Venison findVenisonById(Long id) {
        return venisonRepository.findById(id).get();
    }

    public void deleteVenison(Long id) throws ValidationException {
        Venison venison = this.venisonRepository.findById(id).get();
        if(!venison.getAppoitments().isEmpty())
            throw  new ValidationException("There are appointments for this venison.");
        this.venisonRepository.deleteVenisonById(id);
    }

    public Venison updateVenison(Venison venison) {
        return this.venisonRepository.save(venison);
    }

    public Venison findByName(String name) {
        return this.venisonRepository.findByName(name);
    }

    public Venison addVenisonDto(Venison venison) {
        return venisonRepository.save(venison);
    }
}
