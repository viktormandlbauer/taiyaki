package at.technikum.taiyaki.backend.mappers;

import at.technikum.taiyaki.backend.dto.ProductDto;
import at.technikum.taiyaki.backend.dto.UserDto;
import at.technikum.taiyaki.backend.entity.Product;
import at.technikum.taiyaki.backend.entity.Users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(Users user);
    Users toEntity(UserDto userDto);
    List<UserDto> toDto(List<Users> entity);
    List<Users> toEntity(List <Users> dto);
}
