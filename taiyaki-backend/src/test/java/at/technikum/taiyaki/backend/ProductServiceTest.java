package at.technikum.taiyaki.backend;

import at.technikum.taiyaki.backend.dto.ProductDto;
import at.technikum.taiyaki.backend.entity.Product;
import at.technikum.taiyaki.backend.mappers.ProductMapper;
import at.technikum.taiyaki.backend.repository.ProductRepository;
import at.technikum.taiyaki.backend.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock private ProductRepository productRepository;
    @Mock private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    private List<Product> products;
    private List<ProductDto> productDtos;

    @BeforeEach
    void setup() {
        products = new ArrayList<>();
        productDtos = new ArrayList<>();

        Product p1 = new Product();
        p1.setName("Product 1");
        p1.setPrice(new BigDecimal("10.00"));
        products.add(p1);

        Product p2 = new Product();
        p2.setName("Product 2");
        p2.setPrice(new BigDecimal("15.00"));
        products.add(p2);

        ProductDto d1 = new ProductDto();
        d1.setName("Product 1");
        d1.setPrice(new BigDecimal("10.00"));
        productDtos.add(d1);

        ProductDto d2 = new ProductDto();
        d2.setName("Product 2");
        d2.setPrice(new BigDecimal("15.00"));
        productDtos.add(d2);
    }

    @Test
    void getProductsBetweenPrices() {
        // Given / Arrange
        when(productRepository.findProductsByPriceBetween(
                new BigDecimal("5.00"), new BigDecimal("20.00")))
                .thenReturn(products);

        when(productMapper.toDto(products)).thenReturn(productDtos);

        // When / Act
        List<ProductDto> result =
                productService.getProductsBetweenPrice(new BigDecimal("5.00"), new BigDecimal("20.00"));

        // Then / Assert
        assertThat(result).hasSize(2);
    }
}
