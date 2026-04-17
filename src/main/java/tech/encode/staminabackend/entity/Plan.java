package tech.encode.staminabackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
