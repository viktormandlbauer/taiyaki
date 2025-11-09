package at.technikum.taiyaki.backend.repository;

import at.technikum.taiyaki.backend.entity.Flavour;
import at.technikum.taiyaki.backend.entity.Product;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends ListCrudRepository<Product, UUID> {
    Product findByproductname(String name);
    }


