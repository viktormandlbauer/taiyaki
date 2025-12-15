package at.technikum.taiyaki.backend.mappers;

import at.technikum.taiyaki.backend.dto.AddressDto;
import at.technikum.taiyaki.backend.dto.auth.RegisterDto;
import at.technikum.taiyaki.backend.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDto toDto(Address address);
    Address toEntity(AddressDto dto);
    Address toEntity(RegisterDto dto);
}