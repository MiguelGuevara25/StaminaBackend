package tech.encode.staminabackend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AttendanceDTO {
    private Long id;
    private LocalDateTime entryDate;
    private UserDTO user;
}
