package at.technikum.taiyaki.backend.service;

import at.technikum.taiyaki.backend.dto.ProductDto;
import at.technikum.taiyaki.backend.dto.order.OrderDetailsDto;
import at.technikum.taiyaki.backend.dto.order.OrderDto;
import at.technikum.taiyaki.backend.dto.order.OrderProductDto;
import at.technikum.taiyaki.backend.dto.order.ProductInOrderDetailsDto;
import at.technikum.taiyaki.backend.entity.Order;
import at.technikum.taiyaki.backend.entity.OrderProduct;
import at.technikum.taiyaki.backend.entity.Product;
import at.technikum.taiyaki.backend.mappers.OrderMapper;
import at.technikum.taiyaki.backend.mappers.ProductMapper;
import at.technikum.taiyaki.backend.repository.OrderProductRepository;
import at.technikum.taiyaki.backend.repository.OrderRepository;
import at.technikum.taiyaki.backend.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper ordersMapper;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductMapper productMapper;

    public List<OrderDto> getAllOrders() {
        return ordersMapper.toDto(orderRepository.findAll());
    }

    public OrderDto createOrder(OrderDto orderDto) {
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

        Product product = productRepository.findById(orderProductDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + orderProductDto.getId()));

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        orderProduct.setQuantity(orderProductDto.getQuantity());

        orderProductRepository.save(orderProduct);
    }

    public OrderDetailsDto getOrderDetails(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found: " + orderId));

        List<OrderProduct> orderProductList = this.orderProductRepository.findAllByOrder(order);

        List<ProductInOrderDetailsDto> productsInOrderDetails = new ArrayList<>();

        BigDecimal totalPrice = BigDecimal.ZERO;

        for(OrderProduct orderProduct: orderProductList){
            Product product = orderProduct.getProduct();
            productsInOrderDetails.add(productMapper.toProductInOrderDetailsDto(product, orderProduct.getQuantity()));
            totalPrice = totalPrice.add(product.getPrice().multiply(BigDecimal.valueOf(orderProduct.getQuantity())));
        }

        OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
        orderDetailsDto.setProducts(productsInOrderDetails);
        orderDetailsDto.setTotalPrice(totalPrice);
        return orderDetailsDto;
    }

    public void removeProductFromOrder(UUID orderId, UUID productId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found: " + orderId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + productId));

        List<OrderProduct> orderProduct = orderProductRepository.findByProductAndOrder(product, order);
        orderProductRepository.deleteAll(orderProduct);
    }
}
