package at.technikum.taiyaki.backend.controller;

import at.technikum.taiyaki.backend.dto.UserDto;
import at.technikum.taiyaki.backend.entity.Users;
import at.technikum.taiyaki.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers(){
        return userService.getUsers();
    }

    @GetMapping
    public UserDto getUserById(UUID id){
        return userService.getUserById(id).orElse(null);
    }

    @GetMapping
    public Users getUserByUsername(String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping
    public Users getUserByEmail(String email){
        return userService.getUserByEmail(email);
    }

    @PostMapping
    public UserDto createUser(UserDto userDto){
        return userService.createUser(userDto);
    }

    @DeleteMapping
    public Boolean deleteUser(UUID id){
        userService.deleteUser(id);
        return true;
    }

    @PutMapping
    public Boolean updateUser(UUID id, UserDto userDto){
        userService.updateUser(id, userDto);
        return true;
    }
}