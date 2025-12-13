package at.technikum.taiyaki.backend.service;

import at.technikum.taiyaki.backend.dto.profile.ProfileDto;
import at.technikum.taiyaki.backend.dto.UserDto;
import at.technikum.taiyaki.backend.dto.auth.RegisterDto;
import at.technikum.taiyaki.backend.dto.profile.UpdateProfileDto;
import at.technikum.taiyaki.backend.entity.Address;
import at.technikum.taiyaki.backend.entity.User;
import at.technikum.taiyaki.backend.exceptions.AddressNotFoundException;
import at.technikum.taiyaki.backend.exceptions.RegistrationException;
import at.technikum.taiyaki.backend.exceptions.UserNotFoundException;
import at.technikum.taiyaki.backend.mappers.RegistrationMapper;
import at.technikum.taiyaki.backend.mappers.UserMapper;
import at.technikum.taiyaki.backend.repository.AddressRepository;
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
    private final AddressRepository addressRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationMapper registrationMapper;


    public List<UserDto> getUsers(){
        return userMapper.toDto(userRepository.findAll());
    }

    public Optional<UserDto> getUserById(UUID id){
        return userRepository.findById(id).map(userMapper::toDto);
    }

    public ProfileDto getUserProfile(UUID id){
        User user = userRepository.findById(id).orElseThrow(() ->new UserNotFoundException(id.toString()));
        Address address = addressRepository.findById(user.getAddress().getId()).orElseThrow(() ->new AddressNotFoundException((user.getAddress().toString())));
        return userMapper.toProfileDto(user, address);
    }

    public boolean updateUserProfile(UUID id, @Valid UpdateProfileDto updateProfileDto){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));

        Address address = addressRepository.findById(user.getAddress().getId())
                .orElseThrow(() -> new AddressNotFoundException(String.valueOf(user.getAddress().getId())));

        userMapper.updateUser(updateProfileDto, user);
        userMapper.updateAddress(updateProfileDto, address);

        addressRepository.save(address);
        userRepository.save(user);

        return true;
    }

    public boolean createUser(RegisterDto registerDto){
        User user = registrationMapper.toEntity(registerDto);

        if(userRepository.findByEmail(registerDto.getEmail()) != null) throw new RegistrationException("Email already exists");
        if(userRepository.findByUsername(registerDto.getUsername()) != null) throw new RegistrationException("Username already exists");

        String hashedPassword = passwordEncoder.encode(registerDto.getPassword());
        user.setPassword(hashedPassword);
        user.setRole("ROLE_USER");

        userRepository.save(user);

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