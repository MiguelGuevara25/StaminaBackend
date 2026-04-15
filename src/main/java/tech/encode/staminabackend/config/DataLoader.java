package tech.encode.staminabackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech.encode.staminabackend.entity.Role;
import tech.encode.staminabackend.repository.IRoleRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final IRoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");
    }

    private void createRoleIfNotFound(String name) {
        Optional<Role> role = roleRepository.findByName(name);

        if (role.isEmpty()) {
            Role newRole = new Role();
            newRole.setName(name);
            roleRepository.save(newRole);
            System.out.println("Rol creado: " + name);
        }
    }
}
