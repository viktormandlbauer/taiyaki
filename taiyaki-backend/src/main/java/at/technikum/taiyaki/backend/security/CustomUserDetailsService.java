package at.technikum.taiyaki.backend.security;

import at.technikum.taiyaki.backend.entity.User;
import at.technikum.taiyaki.backend.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        User user = userService.findByIdentifier(identifier);
        return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }
}