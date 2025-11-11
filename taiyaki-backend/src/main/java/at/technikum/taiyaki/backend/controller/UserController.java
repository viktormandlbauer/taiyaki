package at.technikum.taiyaki.backend.controller;

import at.technikum.taiyaki.backend.dto.UserDto;
import at.technikum.taiyaki.backend.entity.User;
import at.technikum.taiyaki.backend.service.UserService;
import jakarta.validation.Valid;
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

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable UUID id) {
        return userService.getUserById(id).orElse(null);
    }

    /* TODO: Fixing
    @GetMapping("/{username}")
    public Users getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/{email}")
    public Users getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
    */

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
        return true;
    }

    @PutMapping("/{id}")
    public Boolean updateUser(@PathVariable UUID id, @RequestBody @Valid UserDto userDto){
        userService.updateUser(id, userDto);
        return true;
    }
}