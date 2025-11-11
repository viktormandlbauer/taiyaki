package at.technikum.taiyaki.backend.dto.order;

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
    private BigDecimal price;
    private Integer quantity;
}
