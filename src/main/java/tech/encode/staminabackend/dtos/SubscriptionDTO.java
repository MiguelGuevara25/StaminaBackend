package tech.encode.staminabackend.dtos;

import lombok.Getter;
import lombok.Setter;
import tech.encode.staminabackend.entity.Plan;
import tech.encode.staminabackend.entity.User;

import java.time.LocalDate;

@Getter
@Setter
public class SubscriptionDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private User user;
    private Plan plan;
}
