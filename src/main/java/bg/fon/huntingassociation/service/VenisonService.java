package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Venison;
import bg.fon.huntingassociation.domain.dtos.VenisonDto;
import bg.fon.huntingassociation.exception.VenisonNotFoundException;
import bg.fon.huntingassociation.mappers.VenisonMapper;
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
    private final VenisonMapper venisonMapper;

    private final Logger LOG = LoggerFactory.getLogger(VenisonService.class);

    @Autowired
    public VenisonService(VenisonRepository venisonRepository, VenisonMapper venisonMapper) {
        this.venisonRepository = venisonRepository;
        this.venisonMapper = venisonMapper;
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

    public Venison updateVenison(Venison venison) {
        return this.venisonRepository.save(venison);
    }

    public Venison findByName(String name) {
        return this.venisonRepository.findByName(name);
    }

    public Venison addVenisonDto(VenisonDto venisonDto) {
        LOG.info(venisonDto.getFromDate() + " from date");
        LOG.info(venisonDto.getToDate() + " to date");
        Venison venison = venisonMapper.dtoToEntity(venisonDto);
        LOG.info(venison.getFromDate() + " from date");
        LOG.info(venison.getToDate() + " to date");
        return venisonRepository.save(venison);
    }
}
