package at.technikum.taiyaki.backend.dto.order;

import at.technikum.taiyaki.backend.dto.AddressDto;
import at.technikum.taiyaki.backend.entity.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class OrderDetailsDto {
    AddressDto address;
    BigDecimal totalPrice;
    List<ProductInOrderDetailsDto> products;
}
