package at.technikum.taiyaki.backend.controller;

import at.technikum.taiyaki.backend.dto.order.OrderDetailsDto;
import at.technikum.taiyaki.backend.dto.order.OrderDto;
import at.technikum.taiyaki.backend.dto.order.OrderProductDto;
import at.technikum.taiyaki.backend.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDetailsDto> getOrders() {
        return this.orderService.getAllOrders();
    }

    @PostMapping
    public OrderDetailsDto createOrder(@RequestBody @Valid OrderDetailsDto orderDto) {
        return this.orderService.createOrder(orderDto);
    }

    @DeleteMapping("/{orderId}")
    public boolean deleteOrder(@PathVariable UUID orderId) {
        return this.orderService.deleteOrder(orderId);
    }

    @PutMapping("/{orderId}")
    public OrderDetailsDto updateOrder(@PathVariable UUID orderId, @RequestBody @Valid OrderDetailsDto orderDto) {
        //@TODO
        return null;
    }

    @PostMapping("/{orderId}/add")
    public boolean addProductToOrder(@PathVariable UUID orderId, @RequestBody @Valid OrderProductDto orderProductDto) {
        this.orderService.addProduct(orderId, orderProductDto);
        return true;
    }
}
