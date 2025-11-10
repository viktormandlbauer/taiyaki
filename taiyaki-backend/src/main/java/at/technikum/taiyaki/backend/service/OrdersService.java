package at.technikum.taiyaki.backend.service;

import at.technikum.taiyaki.backend.dto.OrderDto;
import at.technikum.taiyaki.backend.entity.Orders;
import at.technikum.taiyaki.backend.mappers.OrdersMapper;
import at.technikum.taiyaki.backend.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrdersService {

    private final OrdersRepository orderRepository;
    private final OrdersMapper ordersMapper;

    public OrdersService(OrdersRepository orderRepository, OrdersMapper ordersMapper) {
        this.orderRepository = orderRepository;
        this.ordersMapper = ordersMapper;
    }

    public List<OrderDto> getAllOrders() {
        return ordersMapper.toDto(orderRepository.findAll());
    }

    public OrderDto createOrder(OrderDto orderDto) {
        Orders orders = ordersMapper.toEntity(orderDto);
        return ordersMapper.toDto(this.orderRepository.save(orders));
    }

    public boolean deleteOrder(UUID orderId) {
        Orders order = this.orderRepository.getOrdersById(orderId);
        this.orderRepository.delete(order);
        return true;
    }

    public OrderDto updateOrder(UUID orderId, OrderDto orderDto) {
        Orders order = this.orderRepository.getOrdersById(orderId);
        return this.ordersMapper.toDto(this.orderRepository.save(ordersMapper.toEntity(orderDto)));
    }
}
