package at.technikum.taiyaki.backend.security.jwt;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {

    @NotBlank(message = "security.jwt.secret must not be blank")
    private String secret;
}
