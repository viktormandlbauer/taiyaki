package at.technikum.taiyaki.backend.mappers;

import at.technikum.taiyaki.backend.dto.OrderDto;
import at.technikum.taiyaki.backend.dto.UserDto;
import at.technikum.taiyaki.backend.entity.Orders;
import at.technikum.taiyaki.backend.entity.Users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdersMapper {
    OrderDto toDto(Orders user);
    Orders toEntity(OrderDto userDto);
    List<OrderDto> toDto(List<Orders> entity);
    List<Orders> toEntity(List <Orders> dto);
}
