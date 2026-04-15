package tech.encode.staminabackend.service;

import tech.encode.staminabackend.dtos.LoginRequestDTO;
import tech.encode.staminabackend.dtos.LoginResponseDTO;

public interface IAuthService {
    LoginResponseDTO login(LoginRequestDTO loginRequest);
}
