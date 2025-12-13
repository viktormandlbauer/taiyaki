package at.technikum.taiyaki.backend.controller;

import at.technikum.taiyaki.backend.dto.auth.AuthRequestDto;
import at.technikum.taiyaki.backend.dto.auth.RegisterDto;
import at.technikum.taiyaki.backend.dto.auth.TokenResponseDto;
import at.technikum.taiyaki.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody @Valid RegisterDto registerDto) {

        logger.debug(registerDto.toString());

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "token", authService.register(registerDto).getToken()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody @Valid AuthRequestDto authRequestDto) {

        logger.debug(authRequestDto.toString());

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "token", authService.authenticate(authRequestDto).getToken()
        ));
    }
}
