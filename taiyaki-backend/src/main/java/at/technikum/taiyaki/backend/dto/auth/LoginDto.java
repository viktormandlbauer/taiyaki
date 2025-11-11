package at.technikum.taiyaki.backend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor
public class LoginDto {
    String identifier; // username or email
    String password;
}
