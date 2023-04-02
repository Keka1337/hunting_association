package bg.fon.huntingassociation.mappers;

import bg.fon.huntingassociation.domain.Address;
import bg.fon.huntingassociation.domain.dtos.AddressDto;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring")
public interface AddressMapper {

    AddressDto entityToDto(Address address);

}
