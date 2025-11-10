package at.technikum.taiyaki.backend.mappers;

import at.technikum.taiyaki.backend.dto.FlavourDto;
import at.technikum.taiyaki.backend.dto.ProductDto;
import at.technikum.taiyaki.backend.entity.Flavour;
import at.technikum.taiyaki.backend.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlavourMapper {
    FlavourDto toDto(Flavour flavour);
    Flavour toEntity(FlavourDto dto);
    List<FlavourDto> toDto(List<Flavour> entity);
    List<Flavour> toEntity(List<FlavourDto> dto);

    default String toName(Flavour flavour) {
        return flavour == null ? null : flavour.getName();
    }

    default Flavour fromName(String name) {
        return name == null ? null : new Flavour(null, name);
    }
}