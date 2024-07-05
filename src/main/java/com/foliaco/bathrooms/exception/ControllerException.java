package com.foliaco.bathrooms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerException {

    @ExceptionHandler(EmailValidationException.class)
    public ProblemDetail accessDeniedException(EmailValidationException emailValidationException) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, emailValidationException.getMessage());
    }

}
