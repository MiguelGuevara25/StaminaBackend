package tech.encode.staminabackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.encode.staminabackend.dtos.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Este método atrapa específicamente los RuntimeException que lanzamos en los services
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex) {

        HttpStatus status = ex.getMessage().toLowerCase().contains("no se encontró")
                ? HttpStatus.NOT_FOUND
                : HttpStatus.BAD_REQUEST;

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                status.value(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 2. Atrapa errores de permisos (Cuando un USER quiere hacer cosas de ADMIN)
    @ExceptionHandler(AccessDeniedException.class) // 👈 Aquí pones el nuevo método
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex) {
        ErrorResponse error = new ErrorResponse(
                "No tienes permisos para realizar esta acción.",
                HttpStatus.FORBIDDEN.value(), // 403
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
}
