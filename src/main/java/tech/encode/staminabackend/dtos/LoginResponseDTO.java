package tech.encode.staminabackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
//@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String email;
    private Set<String> roles;

    public LoginResponseDTO(String token, String email, Set<String> roles) {
        this.token = token;
        this.email = email;
        this.roles = roles;
    }

    public LoginResponseDTO() {}
}
