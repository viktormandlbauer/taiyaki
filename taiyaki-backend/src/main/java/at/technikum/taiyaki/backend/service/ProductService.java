package at.technikum.taiyaki.backend.service;

import at.technikum.taiyaki.backend.dto.FlavourDto;
import at.technikum.taiyaki.backend.dto.ProductDto;
import at.technikum.taiyaki.backend.dto.ReviewDto;
import at.technikum.taiyaki.backend.entity.Flavour;
import at.technikum.taiyaki.backend.entity.Product;
import at.technikum.taiyaki.backend.entity.Review;
import at.technikum.taiyaki.backend.mappers.FlavourMapper;
import at.technikum.taiyaki.backend.mappers.ProductMapper;
import at.technikum.taiyaki.backend.mappers.ReviewMapper;
import at.technikum.taiyaki.backend.repository.FlavourRepository;
import at.technikum.taiyaki.backend.repository.ProductRepository;
import at.technikum.taiyaki.backend.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final FlavourRepository flavourRepository;

    private final ProductMapper productMapper;
    private final ReviewMapper reviewMapper;
    private final FlavourMapper flavourMapper;


    public ProductService(ProductRepository productRepository, ProductMapper productMapper, ReviewRepository reviewRepository, FlavourRepository flavourRepository, ReviewMapper reviewMapper, FlavourMapper flavourMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.reviewRepository = reviewRepository;
        this.flavourRepository = flavourRepository;
        this.reviewMapper = reviewMapper;
        this.flavourMapper = flavourMapper;
    }

    public List<ProductDto> getAll() {
        return productMapper.toDto(productRepository.findAll());
    }

    public ProductDto createProduct(@Valid ProductDto productDto) {
        //return productMapper.toDto(productRepository.save(productMapper.toEntity(productDto)));

        Flavour flavour = flavourRepository.findFlavourByName(productDto.getFlavour());
        Product product = new Product(productDto.getName(), productDto.getDescription(), productDto.getPrice(), flavour);
        return productMapper.toDto(productRepository.save(product));
    }

    public void deleteProduct(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
        productRepository.delete(product);
    }

    public ProductDto updateProduct(UUID productId, @Valid ProductDto productDto) {

        Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        Flavour flavour = flavourRepository.findFlavourByName(productDto.getFlavour());
        product.setFlavour(flavour);

        return productMapper.toDto(productRepository.save(product));
    }

    public ReviewDto addReview(UUID productId, @Valid ReviewDto reviewDto) {

        Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);

        Review review = new Review(product, reviewDto);

        return reviewMapper.toDto(reviewRepository.save(review));
    }

    public List<ReviewDto> findReviewByProduct(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));

        return reviewMapper.toDto(reviewRepository.findReviewByProduct(product));
    }

    public List<FlavourDto> getAllFlavours() {
        return flavourMapper.toDto(flavourRepository.findAll());
    }

    public FlavourDto addFlavour(@Valid FlavourDto flavourDto) {
        Flavour flavour = flavourMapper.toEntity(flavourDto);
        flavourRepository.save(flavour);
        return flavourMapper.toDto(flavour);
    }

    public void deleteProductReview(UUID reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(EntityNotFoundException::new);
        reviewRepository.delete(review);
    }

    public ReviewDto updateProductReview(UUID reviewId, ReviewDto reviewDto) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(EntityNotFoundException::new);
        review.setReviewTitle(reviewDto.getReviewTitle());
        review.setReviewText(reviewDto.getReviewText());
        return this.reviewMapper.toDto(this.reviewRepository.save(review));
    }

    public ProductDto getProduct(UUID productId) {
        return this.productMapper.toDto(productRepository.findById(productId).orElse(null));
    }
}
