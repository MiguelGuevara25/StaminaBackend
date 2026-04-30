package tech.encode.staminabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StaminaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaminaBackendApplication.class, args);
    }

}
