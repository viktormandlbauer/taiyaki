package at.technikum.taiyaki.backend.dto.order;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class OrderProductDto {
    private UUID id;
    @Min(0)
    int quantity;
}
