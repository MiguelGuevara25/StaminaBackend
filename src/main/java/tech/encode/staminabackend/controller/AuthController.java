package tech.encode.staminabackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.encode.staminabackend.dtos.LoginRequestDTO;
import tech.encode.staminabackend.dtos.LoginResponseDTO;
import tech.encode.staminabackend.service.IAuthService;

@RestController
@RequestMapping("/auth")
//@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        // El servicio se encarga de validar; si falla, el GlobalExceptionHandler atrapará el error
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
