package com.game.validateUser;


import java.util.HashMap;
import java.util.Map;

public class LoginValidator {

    public static Map<String, String> validateLogin(String email, String password) {
        Map<String, String> errors = new HashMap<>();

        // Validate email
        if (email == null || email.trim().isEmpty()) {
            errors.put("emailError", "L'email ne peut pas être vide.");
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.put("emailError", "Email non valide.");
        }

        // Validate password
        if (password == null || password.trim().isEmpty()) {
            errors.put("passwordError", "Le mot de passe ne peut pas être vide.");
        }

        return errors;
    }
}

