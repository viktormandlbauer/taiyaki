package at.technikum.taiyaki.backend.controller;

import at.technikum.taiyaki.backend.dto.FlavourDto;
import at.technikum.taiyaki.backend.dto.ProductDto;
import at.technikum.taiyaki.backend.dto.ReviewDto;
import at.technikum.taiyaki.backend.entity.Review;
import at.technikum.taiyaki.backend.mappers.ProductMapper;
import at.technikum.taiyaki.backend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts(){
        return this.productService.getAll();
    }

    @GetMapping("/{productId}")
    public ProductDto getProductById(@PathVariable UUID productId){
        return productService.getProduct(productId);
    }

    @PostMapping
    public ProductDto createProduct (@RequestBody @Valid ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct (@PathVariable UUID productId) {
        productService.deleteProduct(productId);
    }

    @PutMapping("/{productId}")
    public ProductDto UpdateProduct (@PathVariable UUID productId, @RequestBody @Valid ProductDto productDto) {
        return productService.updateProduct(productId, productDto);
    }

    // Mapping for reviews

    @GetMapping("/review/{productId}")
    public List<ReviewDto> findReviewByProduct (@PathVariable UUID productId) {
        return productService.findReviewByProduct(productId);
    }

    @PostMapping("/review/{productId}")
    public ReviewDto addReview (@PathVariable UUID  productId, @RequestBody @Valid ReviewDto reviewDto) {
        return productService.addReview(productId, reviewDto);
    }

    @DeleteMapping("/review/{reviewId}")
    public void deleteReview (@PathVariable UUID reviewId) {
        productService.deleteProductReview(reviewId);
    }

    @PutMapping("/review/{reviewId}")
    public ReviewDto updateReview (@PathVariable UUID reviewId, @RequestBody @Valid ReviewDto reviewDto) {
        return productService.updateProductReview(reviewId, reviewDto);
    }

    // Mapping for flavours

    @GetMapping("/flavours")
    public List<FlavourDto> findAllFlavours() {
        return productService.getAllFlavours();
    }

    @PostMapping("/flavours")
    @ResponseStatus(HttpStatus.CREATED)
    public FlavourDto addFlavour (@RequestBody @Valid FlavourDto flavourDto) {
        return productService.addFlavour(flavourDto);
    }
}
