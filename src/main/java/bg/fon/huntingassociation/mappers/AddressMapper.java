package bg.fon.huntingassociation.mappers;

import bg.fon.huntingassociation.domain.Address;
import bg.fon.huntingassociation.domain.dtos.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "address", expression = "java(address.getStreet() + \", \" + address.getCity())")
    AddressDto entityToDto(Address address);

}
