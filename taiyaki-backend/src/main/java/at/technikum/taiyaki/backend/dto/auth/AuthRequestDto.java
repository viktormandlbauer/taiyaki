package at.technikum.taiyaki.backend.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString(exclude = {"password"})
public class AuthRequestDto {

    @NotBlank
    private String identifier;

    @NotBlank
    private String password;

}