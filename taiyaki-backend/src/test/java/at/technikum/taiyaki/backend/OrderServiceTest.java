package at.technikum.taiyaki.backend;

import at.technikum.taiyaki.backend.dto.order.OrderDto;
import at.technikum.taiyaki.backend.dto.order.OrderProductDto;
import at.technikum.taiyaki.backend.entity.Order;
import at.technikum.taiyaki.backend.entity.OrderProduct;
import at.technikum.taiyaki.backend.mappers.OrderMapper;
import at.technikum.taiyaki.backend.mappers.ProductMapper;
import at.technikum.taiyaki.backend.repository.OrderProductRepository;
import at.technikum.taiyaki.backend.repository.OrderRepository;
import at.technikum.taiyaki.backend.repository.ProductRepository;
import at.technikum.taiyaki.backend.service.OrderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderProductRepository orderProductRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private OrderService orderService;

    private static List<Order> orders = new ArrayList<>();
    private static List<OrderDto> orderDtos = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        Order order1 = new Order();
        order1.setId(UUID.randomUUID());
        orders.add(order1);

        OrderDto orderDto1 = new OrderDto();
        orderDto1.setId(order1.getId());
        orderDtos.add(orderDto1);
    }

    @Test
    void createOrder_shouldReturnCreatedOrder() {
        // given/arrange
        OrderDto inputDto = new OrderDto();
        OrderDto expectedDto = new OrderDto();
        Order order = new Order();
        when(orderMapper.toEntity(inputDto)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);
        when(orderMapper.toDto(order)).thenReturn(expectedDto);

        // when/act
        OrderDto result = orderService.createOrder(inputDto);

        // then/assert
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedDto);
    }

    @Test
    void getAllOrders_shouldReturnOrderList() {
        // given/arrange
        when(orderRepository.findAll()).thenReturn(orders);
        when(orderMapper.toDto(orders)).thenReturn(orderDtos);

        // when/act
        List<OrderDto> result = orderService.getAllOrders();

        // then/assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
    }

    @Test
    void addProduct_shouldAddProductToOrder() {
    }
}
