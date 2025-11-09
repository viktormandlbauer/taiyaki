package at.technikum.taiyaki.backend.service;

import at.technikum.taiyaki.backend.dto.ProductDto;
import at.technikum.taiyaki.backend.mappers.ProductMapper;
import at.technikum.taiyaki.backend.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> getAll() {
        return productMapper.toDto(productRepository.findAll());
    }


    public ProductDto createProduct(@Valid ProductDto productDto) {
        return productMapper.toDto(productRepository.save(productMapper.toEntity(productDto)));
    }
}
