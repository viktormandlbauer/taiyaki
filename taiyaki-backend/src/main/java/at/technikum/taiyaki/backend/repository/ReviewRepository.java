package at.technikum.taiyaki.backend.repository;

import at.technikum.taiyaki.backend.entity.Product;
import at.technikum.taiyaki.backend.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findReviewByProduct(Product product);
}
