package tech.encode.staminabackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "plans")
@Data
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Ejemplo: "Mensual", "Anual"

    @Column(nullable = false)
    private Integer durationDays; // Ejemplo: 30, 90, 365

    @Column(nullable = false)
    private Double price;
}
