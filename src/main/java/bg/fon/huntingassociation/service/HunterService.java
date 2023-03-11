package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Hunter;
import bg.fon.huntingassociation.exception.HunterNotFoundException;
import bg.fon.huntingassociation.repository.HunterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class HunterService {

    private final HunterRepository hunterRepository;

    @Autowired
    public HunterService(HunterRepository hunterRepository) {
        this.hunterRepository = hunterRepository;
    }

    public Hunter addHunter(Hunter hunter) throws ValidationException {
        if(hunterRepository.findByJmbg(hunter.getJmbg()) == null)
            return hunterRepository.save(hunter);
        else
            throw new ValidationException("Hunter with JMBG: " + hunter.getJmbg() + " already exist!");
    }

    public List<Hunter> findAllHunters() {
        return hunterRepository.findAll();
    }

    public Hunter updateHunter(Hunter hunter) {
        return hunterRepository.save(hunter);
    }

    public Hunter findHunterById(Long id) throws HunterNotFoundException {
        return hunterRepository.findHunterById(id).orElseThrow(
                () -> new HunterNotFoundException("Hunter with id + " + id + " is not found."));
    }

    public void deleteHunter(Long hunterId) {
        hunterRepository.deleteHunterById(hunterId);
    }
}
