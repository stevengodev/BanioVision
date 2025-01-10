package com.foliaco.bathrooms.infrastructure.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("No tienes los permisos requeridos");
    }

}
