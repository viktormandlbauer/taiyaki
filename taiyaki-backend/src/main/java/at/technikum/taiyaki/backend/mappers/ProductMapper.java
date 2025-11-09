package at.technikum.taiyaki.backend.mappers;

import at.technikum.taiyaki.backend.dto.ProductDto;
import at.technikum.taiyaki.backend.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = { FlavourMapper.class, ReviewMapper.class })
public interface ProductMapper {
    ProductDto toDto(Product entity);
    Product toEntity(ProductDto dto);
    List <ProductDto> toDto(List<Product> entity);
    List<Product> toEntity(List <ProductDto> dto);
}
