package at.technikum.taiyaki.backend.repository;

import at.technikum.taiyaki.backend.entity.Users;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends ListCrudRepository<Users, UUID> {
    Users findByUsername(String username);
    Users findByEmail(String email);
}