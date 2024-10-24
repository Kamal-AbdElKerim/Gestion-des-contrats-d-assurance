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
import java.util.List;

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
    public String showAutomobileInsuranceForm(Model model , HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Automobile> Automobile = automobileService.getAllAutomobile();
            model.addAttribute("Automobile", Automobile);
            return "demandeAutomobileDevis";
        }
        return "redirect:/login";
    }

    @PostMapping("/automobile")
    public String submitAutomobileInsurance(@ModelAttribute Automobile automobile, Devis devis, Model model ,  HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        automobile.setUser(user);
        double montant = automobile.calculerMontant();
        automobile.setTypeAssurance(TypeAssurance.AUTOMOBILE);

        automobileService.saveAutomobile(automobile);

        // Now create the Devis instance
        devis.setAutomobile(automobile);
        devis.setMontant(montant);
        devis.setTypeAssurance(TypeAssurance.AUTOMOBILE);
        devis.setStatus(DevisStatus.PENDING);

        devisService.saveDevis(devis); // Save Devis

        return "redirect:/automobile";
    }

    @GetMapping("/sante")
    public String showHealthInsuranceForm(Model model , HttpSession session) {


        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Sante> Santes = santeService.getAllSante();
            model.addAttribute("Santes", Santes);
            return "demandeSantesDevis";
        }
        return "redirect:/login";
    }

    @PostMapping("/sante")
    public String submitHealthInsurance(@ModelAttribute Sante sante, Model model , HttpSession session , Devis devis ) throws Exception {
        User user = (User) session.getAttribute("user");
        sante.setUser(user);
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
        devis.setSante(sante); // Link to the saved Sante instance
        devis.setMontant(montant);
        devis.setTypeAssurance(TypeAssurance.SANTE);
        devis.setStatus(DevisStatus.PENDING);

        devisService.saveDevis(devis); // Save Devis

        return "redirect:/sante";
    }

    @GetMapping("/habitation")
    public String showHomeInsuranceForm(Model model , HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Habitation> Habitation = habitationService.getAllHabitation();
            model.addAttribute("Habitation", Habitation);
            return "demandeHabitationDevis";
        }
        return "redirect:/login";
    }

    @PostMapping("/habitation")
    public String submitHomeInsurance(@ModelAttribute Habitation habitation, HttpSession session , Model model ,Devis devis) throws Exception {
        habitationService.saveHabitation(habitation);
        User user = (User) session.getAttribute("user");
        habitation.setUser(user);

        // Calculate the insurance amount
        double montant = habitation.calculerMontant();
        habitation.setTypeAssurance(TypeAssurance.HABITATION);

        // Save the Sante instance first
        habitationService.saveHabitation(habitation);

        // Now create the Devis instance
        devis.setHabitation(habitation); // Link to the saved Sante instance
        devis.setMontant(montant);
        devis.setTypeAssurance(TypeAssurance.HABITATION);
        devis.setStatus(DevisStatus.PENDING);

        devisService.saveDevis(devis); // Save Devis

        return "redirect:/habitation";
    }
}
