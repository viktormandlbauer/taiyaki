package at.technikum.taiyaki.backend.dto.auth;

import at.technikum.taiyaki.backend.dto.AddressDto;
import at.technikum.taiyaki.backend.dto.UserDto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor
public class RegisterDto {

    @NotBlank
    @Size(max = 50)
    private String firstname;

    @NotBlank
    @Size(max = 50)
    private String lastname;

    @NotBlank
    @Size(max = 30)
    private String username;

    @NotBlank
    @Size(max = 100)
    private String password;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(max = 100)
    private String street;

    @NotBlank
    @Size(max = 50)
    private String city;

    @NotBlank
    @Size(max = 10)
    private String postalCode;

    @NotBlank
    @Size(max = 50)
    private String country;
}