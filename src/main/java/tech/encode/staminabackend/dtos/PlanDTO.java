package tech.encode.staminabackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanDTO {
    private Long id;
    private String name; // Ejemplo: "Mensual", "Anual"
    private Integer durationDays; // Ejemplo: 30, 90, 365
    private Double price;
    private String description;
}
