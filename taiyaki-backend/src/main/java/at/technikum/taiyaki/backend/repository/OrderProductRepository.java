package at.technikum.taiyaki.backend.repository;

import at.technikum.taiyaki.backend.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {
}
