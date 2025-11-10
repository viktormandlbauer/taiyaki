package at.technikum.taiyaki.backend.controller;

import at.technikum.taiyaki.backend.dto.auth.LoginDto;
import at.technikum.taiyaki.backend.dto.auth.RegisterDto;
import at.technikum.taiyaki.backend.service.AuthService;
import at.technikum.taiyaki.backend.service.UserService;
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

    @PostMapping("/login")
    public boolean login(@RequestBody LoginDto loginDto) {
        // @TODO
        return false;
    }

    @PostMapping("/register")
    public boolean register(@RequestBody RegisterDto registerDto) {
        // @TODO
        return false;
    }
}
