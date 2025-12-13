package at.technikum.taiyaki.backend.controller;

import at.technikum.taiyaki.backend.dto.order.OrderDetailsDto;
import at.technikum.taiyaki.backend.dto.order.OrderDto;
import at.technikum.taiyaki.backend.dto.order.OrderProductDto;
import at.technikum.taiyaki.backend.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

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
    public OrderDetailsDto updateOrder(@PathVariable UUID orderId, @RequestBody @Valid OrderDetailsDto orderDto) {
        //@TODO
        return null;
    }

    @PostMapping("/{orderId}/add")
    public void addProductToOrder(@PathVariable UUID orderId, @RequestBody @Valid OrderProductDto orderProductDto) {
        this.orderService.addProduct(orderId, orderProductDto);
    }

    @DeleteMapping("/{orderId}/remove/{productId}")
    public void removeProductFromOrder(@PathVariable UUID orderId, @PathVariable UUID productId) {
        this.orderService.removeProductFromOrder(orderId, productId);
    }

    @GetMapping("/{orderId}/details")
    public OrderDetailsDto getOrderDetails(@PathVariable UUID orderId) {
        return this.orderService.getOrderDetails(orderId);
    }
}
