package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Hunter;
import bg.fon.huntingassociation.repository.HunterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HunterService {

    private final HunterRepository hunterRepository;
    private final TeamService teamService;

    @Autowired
    public HunterService(HunterRepository hunterRepository, TeamService teamService) {
        this.hunterRepository = hunterRepository;
        this.teamService = teamService;
    }

    public List<Hunter> findAllHunters() {
        return hunterRepository.findAll();
    }

    public Hunter findHunterById(Long id){
        return hunterRepository.findById(id).get();
    }

    public void deleteHunter(Long hunterId) {
        Hunter hunter = this.hunterRepository.findById(hunterId).get();
        if(hunter.getTeam()!=null)
            this.teamService.updateNumberOfMembers(hunter.getTeam().getId(),-1);
        hunterRepository.deleteById(hunterId);
    }

    public ArrayList<Hunter> findHunterByTeamId(Long teamId) {
        return hunterRepository.findAllByTeamId(teamId);
    }

    public Hunter addHunter(Hunter hunter) throws ValidationException {
        if(hunterRepository.findByJmbg(hunter.getJmbg()) == null
                && hunterRepository.findByLicenceNum(hunter.getLicenceNum()) == null){
            teamService.updateNumberOfMembers(hunter.getTeam().getId(), +1);
            return hunterRepository.save(hunter);
        }
        else
            throw new ValidationException("Hunter with provided jmbg or licence number already exists!");
    }

    public Hunter editHunter(Hunter hunter) throws ValidationException {
        if(hunterRepository.findByJmbg(hunter.getJmbg()) == null
                && hunterRepository.findByLicenceNum(hunter.getLicenceNum()) == null){
            throw new ValidationException("Hunter with provided jmbg or licence number does not exists!");
        }
        else{
            return hunterRepository.save(hunter);
        }
    }
}
