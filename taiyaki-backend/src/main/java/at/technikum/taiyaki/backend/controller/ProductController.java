package at.technikum.taiyaki.backend.controller;

import at.technikum.taiyaki.backend.service.ProductService;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;


}
