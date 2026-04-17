package tech.encode.staminabackend.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import tech.encode.staminabackend.security.JwtRequestFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // 1. Permite TODOS los orígenes
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        // 2. Permite todos los métodos comunes
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // 3. Permite todas las cabeceras
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // OJO: Si usas "*" en AllowedOrigins, NO puedes poner setAllowCredentials(true)
        // por seguridad del navegador. Si lo necesitas, usa AllowedOriginPatterns.
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex.authenticationEntryPoint((request, response, authException) -> {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: No autorizado. Debes iniciar sesión.");
                }))
                .authorizeHttpRequests(auth -> auth
                        // 1. ZONA PÚBLICA: Login y Registro
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/api/users").permitAll()
                        .requestMatchers("/api/users/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                        // 2. ZONA DE PLANES:
                        // El GET es para todos (USER y ADMIN) para que puedan ver qué comprar
                        .requestMatchers(HttpMethod.GET, "/api/plans", "/api/plans/**").hasAnyRole("USER", "ADMIN")
                        // POST, PUT y DELETE solo para el que manda en el gym
                        .requestMatchers(HttpMethod.POST, "/api/plans/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/plans/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/plans/**").hasRole("ADMIN")

                        // 3. ZONA DE USUARIOS:
                        // Solo el Admin debería poder ver la lista de todos los socios
                        //.requestMatchers(HttpMethod.GET, "/api/users", "/api/users/**").hasRole("ADMIN")

                        // 4. EL RESTO: Solo con Token
                        .anyRequest().authenticated()
                );
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
