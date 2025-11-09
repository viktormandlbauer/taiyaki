package at.technikum.taiyaki.backend.mappers;

import at.technikum.taiyaki.backend.dto.FlavourDto;
import at.technikum.taiyaki.backend.entity.Flavour;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlavourMapper {
    FlavourDto toDto(Flavour flavour);
    Flavour toEntity(FlavourDto dto);
}
