package at.technikum.taiyaki.backend.repository;

import at.technikum.taiyaki.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAll();
    Order getOrdersById(UUID orderId);
}
