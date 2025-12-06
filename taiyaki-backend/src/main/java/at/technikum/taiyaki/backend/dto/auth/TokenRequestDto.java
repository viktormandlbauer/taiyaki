package at.technikum.taiyaki.backend.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRequestDto {

    @NotBlank
    private String identifier;

    @NotBlank
    private String password;

}
