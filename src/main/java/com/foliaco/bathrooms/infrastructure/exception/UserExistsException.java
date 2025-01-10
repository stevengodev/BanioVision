package com.foliaco.bathrooms.infrastructure.exception;

public class UserExistsException extends RuntimeException{

    public UserExistsException() {
        super("El usuario ya existe");
    }

}
