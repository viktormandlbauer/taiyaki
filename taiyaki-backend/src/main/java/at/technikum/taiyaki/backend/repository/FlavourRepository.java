package at.technikum.taiyaki.backend.repository;

import at.technikum.taiyaki.backend.entity.Flavour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FlavourRepository extends JpaRepository<Flavour, UUID> {
    Flavour findFlavourByName(String name);
}

