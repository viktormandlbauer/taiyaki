package at.technikum.taiyaki.backend.repository;

import at.technikum.taiyaki.backend.entity.Flavour;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface FlavourRepository extends ListCrudRepository<Flavour, UUID> {
    Flavour findByFlavourname(String name);
}

