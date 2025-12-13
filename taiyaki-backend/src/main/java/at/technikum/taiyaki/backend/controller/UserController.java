package at.technikum.taiyaki.backend.controller;

import at.technikum.taiyaki.backend.dto.UserDto;
import at.technikum.taiyaki.backend.dto.profile.UpdateProfileDto;
import at.technikum.taiyaki.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin("*")
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserDto getUserById(@PathVariable UUID id) {
        return userService.getUserById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Boolean deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
        return true;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Boolean updateUser(@PathVariable UUID id, @RequestBody @Valid UserDto userDto){
        userService.updateUser(id, userDto);
        return true;
    }

    @GetMapping("/{id}/profile")
    @PreAuthorize("hasPermission(#id, T(at.technikum.taiyaki.backend.entity.User).getName(),'read')")
    public ResponseEntity<Map<String, Object>> getProfileByUserId(@PathVariable UUID id) {
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "data", userService.getUserProfile(id)
        ));
    }

    @PutMapping("/{id}/profile")
    @PreAuthorize("hasPermission(#id, T(at.technikum.taiyaki.backend.entity.User).getName(),'update')")
    public ResponseEntity<Map<String, Object>> updateProfile(@PathVariable UUID id, @RequestBody @Valid UpdateProfileDto updateProfileDto){
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "data", userService.updateUserProfile(id, updateProfileDto)
        ));
    }
}