package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Venison;
import bg.fon.huntingassociation.exception.VenisonNotFoundException;
import bg.fon.huntingassociation.repository.VenisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.List;

@Service
public class VenisonService {

    private final VenisonRepository venisonRepository;

    @Autowired
    public VenisonService(VenisonRepository venisonRepository) {
        this.venisonRepository = venisonRepository;
    }

    public Venison addVenison(Venison venison) {
        return venisonRepository.save(venison);
    }

    public List<Venison> findALlVenisons() {
        return venisonRepository.findAll();
    }

    public Venison findVenisonById(Long id) {
        return venisonRepository.findById(id).orElseThrow(() -> new VenisonNotFoundException("Venison with id: " + id + " does not exist."));
    }

    public void deleteVenison(Long id) {
        this.venisonRepository.deleteVenisonById(id);
    }


    public LocalDate chekDate(String date, Venison venison) throws ValidationException {
        LocalDate localDate = LocalDate.parse(date);

        if (venison.getFromDate().isBefore(localDate) && venison.getToDate().isAfter(localDate))
            return localDate;

        return null;

    }

}
