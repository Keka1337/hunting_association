package bg.fon.huntingassociation.mappers;

import bg.fon.huntingassociation.domain.Appointment;
import bg.fon.huntingassociation.domain.dtos.AppointmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper( componentModel = "spring", uses = {VenisonMapper.class, TeamMapper.class})
public interface AppointmentMapper {
    AppointmentMapper mapper = Mappers.getMapper(AppointmentMapper.class);

    @Mapping(source = "team.name", target = "teamName")
    @Mapping(source = "venison.name", target = "venisonName")
    AppointmentDto entityToDto(Appointment appointment);

}
