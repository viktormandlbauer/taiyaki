package at.technikum.taiyaki.backend.repository;

import at.technikum.taiyaki.backend.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Orders, UUID> {
    List<Orders> findAll();
    Orders getOrdersById(UUID orderId);
}
