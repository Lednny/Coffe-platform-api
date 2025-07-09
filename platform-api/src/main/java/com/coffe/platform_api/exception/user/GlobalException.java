package com.coffe.platform_api.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

// Clase para manejar excepciones globales en la API
// Puedes usar esta clase para manejar excepciones de manera centralizada, como errores de validación

@ControllerAdvice
public class GlobalException {

    public ResponseEntity<String> handleUserNotFoundException(UserNotFound ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
