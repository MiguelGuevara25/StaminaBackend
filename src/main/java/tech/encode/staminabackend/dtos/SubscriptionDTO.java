package tech.encode.staminabackend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SubscriptionDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private UserDTO user;
    private PlanDTO plan;
}
