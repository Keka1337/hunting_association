package bg.fon.huntingassociation.mappers;

import bg.fon.huntingassociation.domain.Venison;
import bg.fon.huntingassociation.domain.dtos.VenisonDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper( componentModel = "spring")
public interface VenisonMapper {
    VenisonMapper mapper = Mappers.getMapper(VenisonMapper.class);

    VenisonDto entityToDto(Venison venison);

    Venison dtoToEntity(VenisonDto venisonDto);
}
