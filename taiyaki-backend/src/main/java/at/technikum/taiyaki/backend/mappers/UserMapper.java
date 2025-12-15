package at.technikum.taiyaki.backend.mappers;

import at.technikum.taiyaki.backend.dto.profile.ProfileDto;
import at.technikum.taiyaki.backend.dto.profile.UpdateProfileDto;
import at.technikum.taiyaki.backend.dto.UserDto;
import at.technikum.taiyaki.backend.entity.Address;
import at.technikum.taiyaki.backend.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    List<UserDto> toDto(List<User> entity);
    List<User> toEntity(List <User> dto);
    ProfileDto toProfileDto(User user, Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(UpdateProfileDto dto, @MappingTarget User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAddress(UpdateProfileDto dto, @MappingTarget Address address);

}
