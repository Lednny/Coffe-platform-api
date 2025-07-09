package com.coffe.platform_api.exception.user;


//Excpción personalizada para manejar el caso de usuario no encontrado
// Puedes lanzar esta excepción en tu controlador o servicio cuando no se encuentre un usuario.
public class UserNotFound extends RuntimeException {
    public UserNotFound(String message) {
        super(message);
    }
    }