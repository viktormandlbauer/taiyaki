package at.technikum.taiyaki.backend.mappers;

import at.technikum.taiyaki.backend.dto.order.OrderDetailsDto;
import at.technikum.taiyaki.backend.dto.order.OrderProductDto;
import at.technikum.taiyaki.backend.entity.Order;
import at.technikum.taiyaki.backend.entity.OrderProduct;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {
    OrderProductDto toDto(OrderProduct orderProduct);
    OrderProduct toEntity(OrderProductDto orderProductDto);
    List<OrderProductDto> toDto(List<OrderProduct> entity);
    List<OrderProduct> toEntity(List <OrderProductDto> dto);
}
