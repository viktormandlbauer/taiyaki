package at.technikum.taiyaki.backend.service;

import at.technikum.taiyaki.backend.dto.order.OrderDetailsDto;
import at.technikum.taiyaki.backend.dto.order.OrderDto;
import at.technikum.taiyaki.backend.dto.order.OrderProductDto;
import at.technikum.taiyaki.backend.entity.Order;
import at.technikum.taiyaki.backend.entity.OrderProduct;
import at.technikum.taiyaki.backend.entity.Product;
import at.technikum.taiyaki.backend.mappers.OrderMapper;
import at.technikum.taiyaki.backend.mappers.OrderProductMapper;
import at.technikum.taiyaki.backend.repository.OrderProductRepository;
import at.technikum.taiyaki.backend.repository.OrderRepository;
import at.technikum.taiyaki.backend.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper ordersMapper;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ProductRepository productRepository, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.ordersMapper = orderMapper;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
    }

    public List<OrderDetailsDto> getAllOrders() {
        return ordersMapper.toDto(orderRepository.findAll());
    }

    public OrderDetailsDto createOrder(OrderDetailsDto orderDto) {
        Order orders = ordersMapper.toEntity(orderDto);
        return ordersMapper.toDto(this.orderRepository.save(orders));
    }

    public boolean deleteOrder(UUID orderId) {
        Order order = this.orderRepository.getOrdersById(orderId);
        this.orderRepository.delete(order);
        return true;
    }

    public void addProduct(UUID orderId, OrderProductDto orderProductDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found: " + orderId));

        Product product = productRepository.findById(orderProductDto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + orderProductDto.getProductId()));

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        orderProduct.setQuantity(orderProductDto.getQuantity());

        orderProductRepository.save(orderProduct);
    }
}
