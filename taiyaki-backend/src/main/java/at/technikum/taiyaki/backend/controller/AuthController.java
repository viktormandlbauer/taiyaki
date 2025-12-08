package at.technikum.taiyaki.backend.controller;

import at.technikum.taiyaki.backend.dto.auth.LoginRequestDto;
import at.technikum.taiyaki.backend.dto.auth.RegisterDto;
import at.technikum.taiyaki.backend.dto.auth.LoginResponseDto;
import at.technikum.taiyaki.backend.service.AuthService;
import at.technikum.taiyaki.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public boolean register(@RequestBody RegisterDto registerDto) {
        return userService.createUser(registerDto);
    }

    @PostMapping("/login")
    public LoginResponseDto token (@RequestBody @Valid final LoginRequestDto loginRequestDto){
        return authService.authenticate(loginRequestDto);
     }
}