package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Venison;
import bg.fon.huntingassociation.exception.VenisonNotFoundException;
import bg.fon.huntingassociation.repository.VenisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenisonService {

    private final VenisonRepository venisonRepository;

    @Autowired
    public VenisonService(VenisonRepository venisonRepository) {
        this.venisonRepository = venisonRepository;
    }

    public Venison addVenison(Venison venison){
        return this.venisonRepository.save(venison);
    }

    public List<Venison> findALlVenisons(){
        return this.venisonRepository.findAll();
    }

    public Venison findVenisonById(Long id){
        return this.venisonRepository.findById(id).orElseThrow(
                () -> new VenisonNotFoundException("Venison with id: " + id + " does not exist."));
    }

    public void deleteVenison(Long id){
        this.venisonRepository.deleteVenisonById(id);
    }

}
