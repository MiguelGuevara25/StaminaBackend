package tech.encode.staminabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.encode.staminabackend.entity.User;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDni(String dni);
    Optional<User> findByEmail(String email);
}
