package at.technikum.taiyaki.backend.mappers;

import at.technikum.taiyaki.backend.dto.order.OrderDetailsDto;
import at.technikum.taiyaki.backend.dto.order.OrderDto;
import at.technikum.taiyaki.backend.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order user);
    Order toEntity(OrderDto userDto);
    List<OrderDto> toDto(List<Order> entity);
    List<Order> toEntity(List <Order> dto);
}
