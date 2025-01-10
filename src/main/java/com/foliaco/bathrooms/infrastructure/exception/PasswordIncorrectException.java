package com.foliaco.bathrooms.infrastructure.exception;

public class PasswordIncorrectException extends RuntimeException{

    public PasswordIncorrectException(){
        super("La contraseña no es correcta.");
    }

}
