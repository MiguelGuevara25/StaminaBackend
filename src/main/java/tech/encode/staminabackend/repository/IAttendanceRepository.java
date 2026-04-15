package tech.encode.staminabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.encode.staminabackend.entity.Attendance;

public interface IAttendanceRepository extends JpaRepository<Attendance, Long> {
}
