package com.game.Controller;

import com.game.entity.User;
import com.game.Service.UserService;
import com.game.validateUser.LoginValidator;
import com.game.validateUser.ValidateUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Page d'inscription
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";  // This will return the register.jsp view
    }

    // Gestion de l'inscription
    @PostMapping("/register")
    public String register( @ModelAttribute("user") User user, Model model) {

        Map<String, String> validationErrors = ValidateUser.validateUser(user);

        if (!validationErrors.isEmpty()) {
            // Pass validation errors to the view
            model.addAttribute("validationErrors", validationErrors);
            model.addAttribute("user", user);

            return "register";
        }

        try {
            userService.registerUser(user);
            model.addAttribute("message", "Inscription réussie ! Veuillez vous connecter.");
            return "login";  // Redirect to login page on success
        } catch (Exception e) {
            model.addAttribute("error", "Une erreur est survenue lors de l'inscription.");
            return "register";  // Return to the registration page on error
        }
    }

    // Page de connexion
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // This will return the login.jsp view
    }

    // Gestion de la connexion
    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            Model model , HttpSession session) {
        // Validate input
        Map<String, String> validationErrors = LoginValidator.validateLogin(email, password);

        if (!validationErrors.isEmpty()) {
            // Pass validation errors to the view
            model.addAttribute("validationErrors", validationErrors);
            model.addAttribute("email", email);
            return "login";
        }

        // Authenticate user
        User user = userService.authenticateUser(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/Dashboard";  // Redirect to the dashboard on successful login
        } else {
            model.addAttribute("loginError", "Identifiants incorrects. Veuillez réessayer.");
            return "redirect:/login";  // Return to the login page with an error
        }
    }


    // Déconnexion
    @GetMapping("/Dashboard")
    public String Dashboard(Model model , HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        System.out.println(user);
        return "Dashboard";  // This will return the login.jsp view
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("message", "Vous avez été déconnecté avec succès.");
        return "login";  // This will return the login.jsp view
    }
}
