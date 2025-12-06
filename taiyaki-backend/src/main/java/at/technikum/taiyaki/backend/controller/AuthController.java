package at.technikum.taiyaki.backend.controller;

import at.technikum.taiyaki.backend.dto.auth.TokenRequestDto;
import at.technikum.taiyaki.backend.dto.auth.RegisterDto;
import at.technikum.taiyaki.backend.dto.auth.TokenResponseDto;
import at.technikum.taiyaki.backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public boolean register(@RequestBody RegisterDto registerDto) {
        // @TODO
        return false;
    }

     @PostMapping("/token")
    public TokenResponseDto token (@RequestBody @Valid final TokenRequestDto tokenRequestDto){
        return authService.authenticate(tokenRequestDto);
     }
}
