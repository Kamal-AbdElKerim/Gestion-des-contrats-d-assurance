package com.game.Controller;

import com.game.entity.Devis;
import com.game.Service.DevisService;
import com.game.entity.User;
import com.game.validateUser.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class DevisController {

    @Autowired
    private DevisService devisService;





    // Method to accept a Devis
    @GetMapping("/AccepteDevis")
    public String acceptDevis(@RequestParam("id") Long id, Model model) {
        try {

            devisService.acceptDevis(id);
            model.addAttribute("message", "Devis accepted successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Error accepting the Devis: " + e.getMessage());
        }

        return "redirect:/sante";
    }
}
