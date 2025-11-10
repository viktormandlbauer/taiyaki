package at.technikum.taiyaki.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private UUID id;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotEmpty
    private String username;

    private String password;

    private String role;

    @NotEmpty
    private String email;

    private String profilePicture;

}