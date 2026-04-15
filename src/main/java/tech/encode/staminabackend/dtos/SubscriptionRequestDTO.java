package tech.encode.staminabackend.dtos;

import lombok.Data;

@Data
public class SubscriptionRequestDTO {
    private Long userId;
    private Long planId;
}
