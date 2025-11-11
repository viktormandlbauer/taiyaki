package at.technikum.taiyaki.backend.mappers;

import at.technikum.taiyaki.backend.dto.order.OrderDetailsDto;
import at.technikum.taiyaki.backend.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDetailsDto toDto(Order user);
    Order toEntity(OrderDetailsDto userDto);
    List<OrderDetailsDto> toDto(List<Order> entity);
    List<Order> toEntity(List <Order> dto);
}
