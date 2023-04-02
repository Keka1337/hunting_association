package bg.fon.huntingassociation.mappers;

import bg.fon.huntingassociation.domain.Hunter;
import bg.fon.huntingassociation.domain.dtos.HunterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper( componentModel = "spring", uses = {TeamMapper.class, AddressMapper.class})
public interface HunterMapper {
    HunterMapper mapper = Mappers.getMapper(HunterMapper.class);

    @Mapping(source = "hunter.address.street", target = "street")
    @Mapping(source = "hunter.address.city", target = "city")
    @Mapping(source = "hunter.address.postalCode", target = "postalCode")
    HunterDto entityToDto(Hunter hunter);

    @Mapping(source = "hunterDto.street", target = "address.street")
    @Mapping(source = "hunterDto.city", target = "address.city")
    @Mapping(source = "hunterDto.postalCode", target = "address.postalCode")
    Hunter dtoToEntity(HunterDto hunterDto);
}
