package at.technikum.taiyaki.backend.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
public class ProductInOrderDetailsDto {
    private UUID id;
    private String name;

    @Positive
    private BigDecimal price;

   @Min(1)
   private Integer quantity;
}
