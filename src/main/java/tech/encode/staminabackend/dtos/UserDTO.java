package tech.encode.staminabackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import tech.encode.staminabackend.entity.Role;

import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String dni;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean active;
    private String status;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Set<Role> roles;
}
