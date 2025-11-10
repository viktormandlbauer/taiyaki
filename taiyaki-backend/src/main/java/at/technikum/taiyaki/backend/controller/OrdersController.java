package at.technikum.taiyaki.backend.controller;

import at.technikum.taiyaki.backend.dto.OrderDto;
import at.technikum.taiyaki.backend.service.OrdersService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrdersService orderService;

    public OrdersController(OrdersService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDto> getOrders() {
        return this.orderService.getAllOrders();
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody @Valid OrderDto orderDto) {
        return this.orderService.createOrder(orderDto);
    }

    @DeleteMapping("/{orderId}")
    public boolean deleteOrder(@PathVariable UUID orderId) {
        return this.orderService.deleteOrder(orderId);
    }

    @PutMapping("/{orderId}")
    public OrderDto updateOrder(@PathVariable UUID orderId, @RequestBody @Valid OrderDto orderDto) {
        return this.orderService.updateOrder(orderId, orderDto);
    }

    @GetMapping("/{orderId}")
    public  getOrderProducts(@PathVariable UUID orderId) {
        this.orderService.getOrderProducts(orderId);
    }
}
