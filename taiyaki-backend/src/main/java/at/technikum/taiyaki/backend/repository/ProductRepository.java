package at.technikum.taiyaki.backend.repository;

import at.technikum.taiyaki.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findProductByName(String name);
    Product findProductById(UUID id);
}