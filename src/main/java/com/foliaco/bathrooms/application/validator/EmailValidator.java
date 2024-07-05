package com.foliaco.bathrooms.application.validator;

public class EmailValidator {
    private static final String REQUIRED_DOMAIN = "@cecar.edu.co";

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return email.endsWith(REQUIRED_DOMAIN);
    }

}
