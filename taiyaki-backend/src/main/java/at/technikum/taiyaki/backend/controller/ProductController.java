package at.technikum.taiyaki.backend.controller;

import at.technikum.taiyaki.backend.dto.ProductDto;
import at.technikum.taiyaki.backend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAllProducts(){
        return this.productService.getAll();
    }

    @PostMapping
    public ProductDto CreateProduct (@RequestBody @Valid ProductDto productDto) {
        return productService.createProduct(productDto);
    }
}
