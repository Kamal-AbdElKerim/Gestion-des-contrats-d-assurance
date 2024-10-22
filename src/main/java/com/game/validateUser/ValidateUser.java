package com.game.validateUser;

import com.game.entity.User;

import java.util.HashMap;
import java.util.Map;

public class ValidateUser {

    public static Map<String, String> validateUser(User user) {
        Map<String, String> errors = new HashMap<>();

        // Validate name
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            errors.put("nameError", "Le nom ne peut pas être vide.");
        }

        // Validate email
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            errors.put("emailError", "L'email ne peut pas être vide.");
        } else if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.put("emailError", "Email non valide.");
        }

        // Validate password
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            errors.put("passwordError", "Le mot de passe ne peut pas être vide.");
        } else if (user.getPassword().length() < 6) {
            errors.put("passwordError", "Le mot de passe doit comporter au moins 6 caractères.");
        }

        // Validate phone number
        if (user.getPhoneNumber() == null || user.getPhoneNumber().trim().isEmpty()) {
            errors.put("phoneNumberError", "Le numéro de téléphone ne peut pas être vide.");
        }

        // Validate address
        if (user.getAddress() == null || user.getAddress().trim().isEmpty()) {
            errors.put("addressError", "L'adresse ne peut pas être vide.");
        }

        return errors;
    }
}
