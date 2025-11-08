package at.technikum.taiyaki.backend.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Flavour {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;
}
