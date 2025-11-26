package at.technikum.taiyaki.backend;

import at.technikum.taiyaki.backend.dto.UserDto;
import at.technikum.taiyaki.backend.entity.User;
import at.technikum.taiyaki.backend.mappers.UserMapper;
import at.technikum.taiyaki.backend.repository.UserRepository;
import at.technikum.taiyaki.backend.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private static List<User> users = new ArrayList<>();
    private static List<UserDto> userDtos = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        User user1 = new User();
        user1.setId(UUID.randomUUID());
        user1.setUsername("user1");
        users.add(user1);

        UserDto userDto1 = new UserDto();
        userDto1.setId(user1.getId());
        userDto1.setUsername(user1.getUsername());
        userDtos.add(userDto1);

        users.add(user1);
    }

    @Test
    void getUserByID_whenUserExists() {
        // give/arrange
        User user = users.get(0);
        UserDto userDto = userDtos.get(0);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userDto);

        // when/act
        Optional<UserDto> result = userService.getUserById(user.getId());

        // then/assert
        assertThat(result).contains(userDto);
    }

    @Test
    void deleteUser_whenUserExists() {
        // give/arrange
        User user = users.get(0);

        // when/act
        userService.deleteUser(user.getId());

        // then/assert
        assertThat(userRepository.findById(user.getId())).isEmpty();
    }

    @Test
    void updateUser_whenUserExists() {
        // give/arrange
        UserDto userDto = userDtos.get(0);
        User user = users.get(0);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);

        // when/act
        UserDto result = userService.updateUser(user.getId(), userDto);

        // then/assert
        assertThat(result).isEqualTo(userDto);
    }
}