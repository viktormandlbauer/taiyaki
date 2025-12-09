package at.technikum.taiyaki.backend.controller;

import at.technikum.taiyaki.backend.dto.UserDto;
import at.technikum.taiyaki.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserDto> getAllUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission(#id, T(at.technikum.taiyaki.backend.entity.User).getName(),'read')")
    public UserDto getUserById(@PathVariable UUID id) {
        return userService.getUserById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
        return true;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasPermission(#id, T(at.technikum.taiyaki.backend.entity.User).getName(),'write')")
    public Boolean updateUser(@PathVariable UUID id, @RequestBody @Valid UserDto userDto){
        userService.updateUser(id, userDto);
        return true;
    }
}