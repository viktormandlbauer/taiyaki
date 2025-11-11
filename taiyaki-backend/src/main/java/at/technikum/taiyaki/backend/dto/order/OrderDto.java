package at.technikum.taiyaki.backend.dto.order;

import at.technikum.taiyaki.backend.dto.AddressDto;
import at.technikum.taiyaki.backend.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class OrderDto {
    private UUID id;
    private UserDto user;
    private AddressDto address;
}
