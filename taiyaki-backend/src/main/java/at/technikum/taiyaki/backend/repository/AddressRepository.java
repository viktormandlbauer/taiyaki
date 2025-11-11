package at.technikum.taiyaki.backend.repository;

import at.technikum.taiyaki.backend.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    List<Address> findAll();
}
