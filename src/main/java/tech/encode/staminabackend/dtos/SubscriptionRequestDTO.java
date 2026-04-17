package tech.encode.staminabackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionRequestDTO {
    private Long userId;
    private Long planId;
}
