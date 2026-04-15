package tech.encode.staminabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.encode.staminabackend.entity.Role;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
