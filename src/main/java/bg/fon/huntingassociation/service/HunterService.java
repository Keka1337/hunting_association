package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Hunter;
import bg.fon.huntingassociation.domain.dtos.HunterDto;
import bg.fon.huntingassociation.exception.HunterNotFoundException;
import bg.fon.huntingassociation.mappers.HunterMapper;
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
    private final HunterMapper hunterMapper;

    @Autowired
    public HunterService(HunterRepository hunterRepository,HunterMapper hunterMapper,TeamService teamService) {
        this.hunterRepository = hunterRepository;
        this.hunterMapper = hunterMapper;
        this.teamService = teamService;
    }

    public Hunter addHunter(Hunter hunter) throws ValidationException {
        if(hunterRepository.findByJmbg(hunter.getJmbg()) == null
        && hunterRepository.findByLicenceNum(hunter.getLicenceNum()) == null){
            return hunterRepository.save(hunter);
        }
        else
            throw new ValidationException("Hunter with provided jmbg or licence number already exists!");
    }


    public List<Hunter> findAllHunters() {
        return hunterRepository.findAll();
    }

    public Hunter updateHunter(Hunter hunter) {
        return hunterRepository.save(hunter);
    }

    public Hunter findHunterById(Long id) throws HunterNotFoundException {
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

    public Hunter updateHunterDto(HunterDto hunterDto) throws ValidationException {
        Hunter existing = this.hunterRepository.findById(hunterDto.getId()).get();
        if(existing.getLicenceNum()!=hunterDto.getLicenceNum()){
            checkLicenceNumber(hunterDto.getLicenceNum());
        }
        Hunter hunter = hunterMapper.dtoToEntity(hunterDto);
        hunter.setTeam(existing.getTeam());
        return hunterRepository.save(hunter);
    }

    private void checkLicenceNumber(String licenceNum) throws ValidationException {
        if(hunterRepository.findByLicenceNum(licenceNum) != null)
            throw new ValidationException("Hunter with provided licence number already exists!");
    }

    public Hunter addHunterDto(HunterDto hunterDto) throws ValidationException {
        Hunter hunter = hunterMapper.dtoToEntity(hunterDto);
        if(hunterRepository.findByJmbg(hunter.getJmbg()) == null
                && hunterRepository.findByLicenceNum(hunter.getLicenceNum()) == null){
            return hunterRepository.save(hunter);
        }
        else
            throw new ValidationException("Hunter with provided jmbg or licence number already exists!");
    }
}
