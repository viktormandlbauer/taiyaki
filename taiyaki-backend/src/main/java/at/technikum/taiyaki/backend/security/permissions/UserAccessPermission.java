package at.technikum.taiyaki.backend.security.permissions;

import at.technikum.taiyaki.backend.entity.User;
import at.technikum.taiyaki.backend.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserAccessPermission implements AccessPermission {
    @Override
    public boolean supports(Authentication authentication, String className) {
        return className.equals(User.class.getName());
    }

    @Override
    public boolean hasPermission(Authentication authentication, UUID resourceId) {
        return ((UserPrincipal) authentication.getPrincipal()).getId().equals(resourceId);
    }
}
