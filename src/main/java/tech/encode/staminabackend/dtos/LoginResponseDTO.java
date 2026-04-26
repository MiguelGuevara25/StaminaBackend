package tech.encode.staminabackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String email;
    private String firstName;
    private String lastName;
    private Set<String> roles;
}
