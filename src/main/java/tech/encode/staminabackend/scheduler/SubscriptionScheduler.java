package tech.encode.staminabackend.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.encode.staminabackend.entity.Subscription;
import tech.encode.staminabackend.repository.ISubscriptionRepository;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubscriptionScheduler {
    private final ISubscriptionRepository subscriptionRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void updateExpiredSubscriptions() {
        List<Subscription> active = subscriptionRepository.findByStatus("ACTIVE");

        List<Subscription> expired = active.stream()
                .filter(s -> s.getEndDate().isBefore(LocalDate.now()))
                .toList();

        expired.forEach(s -> s.setStatus("EXPIRED"));
        subscriptionRepository.saveAll(expired);

        log.info("Suscripciones expiradas actualizadas: {}", expired.size());
    }
}
