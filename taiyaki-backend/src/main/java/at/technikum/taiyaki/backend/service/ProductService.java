package at.technikum.taiyaki.backend.service;

import at.technikum.taiyaki.backend.dto.ProductDto;
import at.technikum.taiyaki.backend.dto.ReviewDto;
import at.technikum.taiyaki.backend.mappers.ProductMapper;
import at.technikum.taiyaki.backend.mappers.ReviewMapper;
import at.technikum.taiyaki.backend.repository.ProductRepository;
import at.technikum.taiyaki.backend.repository.ReviewRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    public List<ProductDto> getAll() {
        return productMapper.toDto(productRepository.findAll());
    }


    public ProductDto createProduct(@Valid ProductDto productDto) {
        return productMapper.toDto(productRepository.save(productMapper.toEntity(productDto)));
    }


    public ReviewDto createReview(@Valid ReviewDto reviewDto) {
        return reviewMapper.toDto(reviewRepository.save(reviewMapper.toEntity(reviewDto)));
    }


    public ProductDto deleteProduct(@Valid ProductDto productDto) {
        productRepository.delete(productMapper.toEntity(productDto));
        return productDto;
    }

    public ProductDto updateProduct(@Valid ProductDto productDto) {
        return productMapper.toDto(productRepository.save(productMapper.toEntity(productDto)));
    }
}
