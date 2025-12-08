package at.technikum.taiyaki.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter @Getter @NoArgsConstructor
public class AddressDto {
    private UUID id;

    @NotBlank
    private String street, city, postalCode, country;
}
