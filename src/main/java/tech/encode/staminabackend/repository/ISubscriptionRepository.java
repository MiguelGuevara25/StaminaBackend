package tech.encode.staminabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.encode.staminabackend.entity.Subscription;

import java.util.List;

public interface ISubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserDni(String dni);
    List<Subscription> findByStatus(String status);
}
