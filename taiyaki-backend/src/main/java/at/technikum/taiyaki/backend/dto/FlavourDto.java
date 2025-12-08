package at.technikum.taiyaki.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter @Getter @NoArgsConstructor
public class FlavourDto {

    private UUID id;

    @NotBlank
    private String name;

}
