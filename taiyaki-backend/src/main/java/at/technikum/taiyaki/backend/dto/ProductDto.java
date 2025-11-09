package at.technikum.taiyaki.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;


@Setter @Getter @NoArgsConstructor
public class ProductDto {

    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;


    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal price;

    @NotNull
    private FlavourDto flavour;

    public ProductDto(UUID id, BigDecimal price, String description, FlavourDto flavour, String name) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.flavour = flavour;
        this.name = name;
    }

    public ProductDto(String description, FlavourDto flavour, BigDecimal price, String name) {
        this.description = description;
        this.flavour = flavour;
        this.price = price;
        this.name = name;
    }
}
