package at.technikum.taiyaki.backend.service;

import at.technikum.taiyaki.backend.dto.UserDto;
import at.technikum.taiyaki.backend.dto.auth.RegisterDto;
import at.technikum.taiyaki.backend.entity.User;
import at.technikum.taiyaki.backend.mappers.RegistrationMapper;
import at.technikum.taiyaki.backend.mappers.UserMapper;
import at.technikum.taiyaki.backend.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationMapper registrationMapper;


    public List<UserDto> getUsers(){
        return userMapper.toDto(userRepository.findAll());
    }

    public Optional<UserDto> getUserById(UUID id){
        return userRepository.findById(id).map(userMapper::toDto);
    }

    public boolean createUser(RegisterDto registerDto){
        User user = registrationMapper.toEntity(registerDto);

        if(userRepository.findByEmail(registerDto.getEmail()) != null) return false;
        if(userRepository.findByUsername(registerDto.getUsername()) != null) return false;

        String hashedPassword = passwordEncoder.encode(registerDto.getPassword());
        user.setPassword(hashedPassword);
        user.setRole("ROLE_USER");

        userRepository.save(user);
        System.out.println("User \"" + user.getUsername() + "\" successfully created.");

        return true;
    }

    public void deleteUser(UUID id){
        userRepository.deleteById(id);
    }

    public UserDto updateUser(UUID id, @Valid UserDto userDto){
        User user = userRepository.findById(id).orElseThrow();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());

        return userMapper.toDto(userRepository.save(user));
    }

    public User findByIdentifier(String identifier) {
        if(identifier.contains("@")) {
            return userRepository.findByEmail(identifier);
        }
        else {
            return userRepository.findByUsername(identifier);
        }
    }
}