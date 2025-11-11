package at.technikum.taiyaki.backend.service;

import at.technikum.taiyaki.backend.dto.UserDto;
import at.technikum.taiyaki.backend.entity.User;
import at.technikum.taiyaki.backend.mappers.UserMapper;
import at.technikum.taiyaki.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getUsers(){
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public Optional<UserDto> getUserById(UUID id){
        return userRepository.findById(id).map(userMapper::toDto);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public UserDto createUser(UserDto userDto){
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    public void deleteUser(UUID id){
        userRepository.deleteById(id);
    }

    public void updateUser(UUID id, UserDto userDto){
        userRepository.save(userMapper.toEntity(userDto));
    }
}