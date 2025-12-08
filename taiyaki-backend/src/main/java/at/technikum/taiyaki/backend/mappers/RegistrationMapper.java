package at.technikum.taiyaki.backend.mappers;

import at.technikum.taiyaki.backend.dto.UserDto;
import at.technikum.taiyaki.backend.dto.auth.RegisterDto;
import at.technikum.taiyaki.backend.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    User toEntity(RegisterDto registerDto);
}
