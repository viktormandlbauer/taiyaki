package at.technikum.taiyaki.backend.service;

import at.technikum.taiyaki.backend.dto.AddressDto;
import at.technikum.taiyaki.backend.dto.auth.RegisterDto;
import at.technikum.taiyaki.backend.dto.auth.AuthRequestDto;
import at.technikum.taiyaki.backend.dto.auth.TokenResponseDto;
import at.technikum.taiyaki.backend.entity.Address;
import at.technikum.taiyaki.backend.entity.User;
import at.technikum.taiyaki.backend.exceptions.RegistrationException;
import at.technikum.taiyaki.backend.mappers.AddressMapper;
import at.technikum.taiyaki.backend.mappers.RegistrationMapper;
import at.technikum.taiyaki.backend.repository.AddressRepository;
import at.technikum.taiyaki.backend.repository.UserRepository;
import at.technikum.taiyaki.backend.security.UserPrincipal;
import at.technikum.taiyaki.backend.security.jwt.TokenIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenIssuer tokenIssuer;
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    private final RegistrationMapper registrationMapper;
    private final AddressMapper addressMapper;

    public TokenResponseDto authenticate(AuthRequestDto authRequest) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getIdentifier(), authRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        String token = tokenIssuer.issue(principal.getId(), principal.getUsername(), principal.getRole());

        return new TokenResponseDto(token);
    }

    public TokenResponseDto register(RegisterDto registerDto) throws RegistrationException {

        User user = registrationMapper.toEntity(registerDto);

        if(userRepository.findByEmail(registerDto.getEmail()) != null) throw new RegistrationException("Email already exists");
        if(userRepository.findByUsername(registerDto.getUsername()) != null) throw new RegistrationException("Username already exists");

        String hashedPassword = passwordEncoder.encode(registerDto.getPassword());
        user.setPassword(hashedPassword);
        user.setRole("ROLE_USER");

        Address address = addressMapper.toEntity(registerDto);
        addressRepository.save(address);

        user.setAddress(address);
        userRepository.save(user);

        return authenticate(new AuthRequestDto(registerDto.getUsername(), registerDto.getPassword()));
    }
}
