package at.technikum.taiyaki.backend.repository;

import at.technikum.taiyaki.backend.entity.Order;
import at.technikum.taiyaki.backend.entity.OrderProduct;
import at.technikum.taiyaki.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {
    List<OrderProduct> findAllByOrder(Order order);
    List<OrderProduct> findByProductAndOrder(Product product, Order order);
}
