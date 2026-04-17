package tech.encode.staminabackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.encode.staminabackend.entity.Role;
import tech.encode.staminabackend.entity.User;
import tech.encode.staminabackend.repository.IRoleRepository;
import tech.encode.staminabackend.repository.IUserRepository;
import tech.encode.staminabackend.service.IUserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements IUserService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        if (userRepository.findByDni(user.getDni()).isPresent()) {
            throw new RuntimeException("El DNI " + user.getDni() + " ya está registrado.");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("El correo " + user.getEmail() + " ya está en uso.");
        }

        if (user.getRoles() == null) {
            user.setRoles(new java.util.HashSet<>());
        }

        if (user.getRoles().isEmpty()) {
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Error: Roles no inicializados en la BD."));
            user.getRoles().add(userRole);
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    @Override
    public User findByDni(String dni) {
        return userRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("No se encontró al socio con DNI: " + dni));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró al socio con ID: " + id));
    }

    @Override
    public User update(Long id, User userDetails) {
        // 1. Buscamos al usuario actual. Si no existe, lanzamos error.
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el socio con ID: " + id));

        // 2. Validar el Email (Para que no choque con otros socios)
        userRepository.findByEmail(userDetails.getEmail()).ifPresent(existingUser -> {
            System.out.println("Este es el existingUser de email" + existingUser);
            // Si el email existe Y el ID es diferente al que estoy editando... ¡ERROR!
            if (!existingUser.getId().equals(id)) {
                throw new RuntimeException("El correo " + userDetails.getEmail() + " ya está en uso por otro socio.");
            }
        });

        // 3. Validar el DNI (Misma lógica)
        userRepository.findByDni(userDetails.getDni()).ifPresent(existingUser -> {
            System.out.println("Este es el existingUser de DNI" + existingUser);

            if (!existingUser.getId().equals(id)) {
                throw new RuntimeException("El DNI " + userDetails.getDni() + " ya pertenece a otro socio.");
            }
        });

        // 4. Settear los datos nuevos al objeto que ya teníamos de la BD
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setDni(userDetails.getDni());
        user.setPhone(userDetails.getPhone());

        // 5. Guardamos el objeto 'user' actualizado
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
