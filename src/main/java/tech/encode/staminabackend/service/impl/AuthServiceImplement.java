package tech.encode.staminabackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.encode.staminabackend.dtos.LoginRequestDTO;
import tech.encode.staminabackend.dtos.LoginResponseDTO;
import tech.encode.staminabackend.entity.Role;
import tech.encode.staminabackend.entity.User;
import tech.encode.staminabackend.repository.IUserRepository;
import tech.encode.staminabackend.security.JwtUtil;
import tech.encode.staminabackend.service.IAuthService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class AuthServiceImplement implements IAuthService {
    private final IUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImplement(IUserRepository userRepository,
                                BCryptPasswordEncoder passwordEncoder,
                                JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        // 1. Extraemos los nombres de los roles PRIMERO
        List<String> rolesList = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        // 2. AHORA generamos el token pasándole los roles
        // Asegúrate de que JwtUtil reciba (String, List<String>)
        String realToken = jwtUtil.generateToken(user.getEmail(), rolesList);

        // 3. Devolvemos la respuesta
        return new LoginResponseDTO(realToken, user.getEmail(), Set.copyOf(rolesList));
    }
}
