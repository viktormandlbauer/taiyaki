package at.technikum.taiyaki.backend.service;

import at.technikum.taiyaki.backend.dto.auth.RegisterDto;
import at.technikum.taiyaki.backend.dto.auth.TokenRequestDto;
import at.technikum.taiyaki.backend.dto.auth.TokenResponseDto;
import at.technikum.taiyaki.backend.security.UserPrincipal;
import at.technikum.taiyaki.backend.security.jwt.TokenIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenIssuer tokenIssuer;

    private final AuthenticationManager authenticationManager;

    public TokenResponseDto authenticate(TokenRequestDto authRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getIdentifier(), authRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        String token = tokenIssuer.issue(principal.getId(), principal.getUsername(), principal.getRole());

        return new TokenResponseDto(token);
    }

    public boolean registerUser(RegisterDto registerDto) {
        //TODO

        return false;
    }
}
