package tech.encode.staminabackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.encode.staminabackend.entity.Attendance;
import tech.encode.staminabackend.entity.Plan;
import tech.encode.staminabackend.entity.Subscription;
import tech.encode.staminabackend.entity.User;
import tech.encode.staminabackend.repository.IAttendanceRepository;
import tech.encode.staminabackend.repository.IPlanRepository;
import tech.encode.staminabackend.repository.ISubscriptionRepository;
import tech.encode.staminabackend.repository.IUserRepository;
import tech.encode.staminabackend.service.ISubscriptionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServicesImplement implements ISubscriptionService {
    private final ISubscriptionRepository subscriptionRepository;
    private final IAttendanceRepository attendanceRepository;
    private final IUserRepository userRepository;
    private final IPlanRepository planRepository;

    @Override
    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription save(Subscription subscription) {
        User user = userRepository.findById(subscription.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Plan plan = planRepository.findById(subscription.getPlan().getId())
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));

        int days = plan.getDurationDays();

        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(subscription.getStartDate().plusDays(days));
        subscription.setUser(user);
        subscription.setPlan(plan);
        subscription.setStatus("ACTIVE");

        return subscriptionRepository.save(subscription);
    }

    @Override
    public String checkAccess(String dni) {
        User user = userRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado con DNI: " + dni));

        List<Subscription> subscriptions = subscriptionRepository.findByUserDni(dni);

        if (subscriptions.isEmpty()) {
            return "DENEGADO: El socio no tiene ninguna suscripción registrada.";
        }

        LocalDate today = LocalDate.now();
        boolean isValid = subscriptions.stream()
                .anyMatch(s -> (s.getEndDate().isAfter(today) || s.getEndDate().isEqual(today))
                        && s.getStatus().equals("ACTIVE"));

        if (isValid) {
            // ¡AQUÍ ESTÁ EL TRUCO!
            // Si el acceso es concedido, guardamos la asistencia antes de responder
            Attendance attendance = new Attendance();
            attendance.setUser(user);
            attendance.setEntryDate(LocalDateTime.now());
            attendanceRepository.save(attendance);
            return "CONCEDIDO: Bienvenido " + user.getFirstName() + " " + user.getLastName();
        } else {
            return "DENEGADO: Membresía vencida.";
        }
    }
}
