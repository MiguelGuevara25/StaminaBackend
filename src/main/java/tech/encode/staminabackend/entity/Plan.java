package tech.encode.staminabackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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
    private BigDecimal price;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private Boolean active = true;
}
