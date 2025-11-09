package at.technikum.taiyaki.backend.mappers;

import at.technikum.taiyaki.backend.dto.FlavourDto;
import at.technikum.taiyaki.backend.dto.ReviewDto;
import at.technikum.taiyaki.backend.entity.Flavour;
import at.technikum.taiyaki.backend.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDto toDto(Review review);
    Review toEntity(ReviewDto dto);
}
