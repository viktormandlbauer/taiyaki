package at.technikum.taiyaki.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public class ProductDto {

    private Integer id;

    @NotBlank
    private String name;
    private FlavourDto flavour;

    @NotNull
    private String description;


    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal price;

    public ProductDto(Integer id, BigDecimal price, String description, FlavourDto flavour, String name) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.flavour = flavour;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FlavourDto getFlavour() {
        return flavour;
    }

    public void setFlavour(FlavourDto flavour) {
        this.flavour = flavour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
