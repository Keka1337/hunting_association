package bg.fon.huntingassociation.mappers;

import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.domain.dtos.TeamDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper( componentModel = "spring")
public interface TeamMapper {
    TeamMapper mapper = Mappers.getMapper(TeamMapper.class);

    TeamDto entityToDto(Team team);
}
