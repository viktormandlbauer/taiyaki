package at.technikum.taiyaki.backend.security.jwt;

import at.technikum.taiyaki.backend.security.UserPrincipal;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JwtToPrincipalConverter {

    public UserPrincipal converter(DecodedJWT decodedJWT) {
        return new UserPrincipal(UUID.fromString(decodedJWT.getSubject()),
                decodedJWT.getClaim("username").asString(),
                "",
                decodedJWT.getClaim("role").asString()
        );
    }
}