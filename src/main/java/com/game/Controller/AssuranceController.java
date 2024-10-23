package com.game.Controller;

import com.game.Service.DevisService;
import com.game.entity.*;
import com.game.Service.AutomobileService;
import com.game.Service.HabitationService;
import com.game.Service.SanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
public class AssuranceController {

    @Autowired
    private AutomobileService automobileService;

    @Autowired
    private HabitationService habitationService;

    @Autowired
    private SanteService santeService;

    @Autowired
    private DevisService devisService;

    @GetMapping("/automobile")
    public String showAutomobileInsuranceForm(Model model) {
        model.addAttribute("automobile", new Automobile());
        return "demande_devis_auto"; // JSP page for automobile insurance
    }

    @PostMapping("/automobile")
    public String submitAutomobileInsurance(@ModelAttribute Automobile automobile, Model model) throws Exception {
        automobileService.saveAutomobile(automobile);
        double montant = automobile.calculerMontant(); // Call your method to calculate premium
        model.addAttribute("montant", montant);
        return "resultat_devis_auto"; // JSP page to display automobile quote
    }

    @GetMapping("/sante")
    public String showHealthInsuranceForm(Model model) {
        model.addAttribute("sante", new Sante());
        return "demande_devis_sante"; // JSP page for health insurance
    }

    @PostMapping("/sante")
    public String submitHealthInsurance(@ModelAttribute Sante sante, Model model , HttpSession session , Devis devis) throws Exception {
        User user = (User) session.getAttribute("user");
        sante.setUser(user);
        System.out.println("TypeCouverture.PREMIUM "+TypeCouverture.PREMIUM);
        // Determine coverage type
        if (sante.getTypeCouverture().equals("PREMIUM")) {
            sante.setTypeCouverture(TypeCouverture.PREMIUM);
        } else {
            sante.setTypeCouverture(TypeCouverture.BASE);
        }

        // Calculate the insurance amount
        double montant = sante.calculerMontant();
        sante.setTypeAssurance(TypeAssurance.SANTE);

        // Save the Sante instance first
        santeService.saveSante(sante);

        // Now create the Devis instance
        devis.setAssurance(sante); // Link to the saved Sante instance
        devis.setMontant(montant);
        devis.setTypeAssurance(TypeAssurance.SANTE);
        devisService.saveDevis(devis); // Save Devis
        model.addAttribute("montant", montant);
        return "resultat_devis_sante"; // JSP page to display health quote
    }

    @GetMapping("/habitation")
    public String showHomeInsuranceForm(Model model) {
        model.addAttribute("habitation", new Habitation());
        return "demande_devis_habitation"; // JSP page for home insurance
    }

    @PostMapping("/habitation")
    public String submitHomeInsurance(@ModelAttribute Habitation habitation, Model model) throws Exception {
        habitationService.saveHabitation(habitation);
        double montant = habitation.calculerMontant(); // Call your method to calculate premium
        model.addAttribute("montant", montant);
        return "resultat_devis_habitation"; // JSP page to display home quote
    }
}
