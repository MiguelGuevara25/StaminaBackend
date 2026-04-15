package tech.encode.staminabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.encode.staminabackend.entity.Plan;

public interface IPlanRepository extends JpaRepository<Plan, Long> {
}
