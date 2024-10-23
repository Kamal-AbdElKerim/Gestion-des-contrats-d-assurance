package com.game.Controller;

import com.game.Service.AutomobileService;
import com.game.Service.DevisService;
import com.game.Service.SanteService;
import com.game.entity.*;
import com.game.Service.ContratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ContratController {

    @Autowired
    private ContratService contratService;
    @Autowired
    private DevisService devisService;
    @Autowired
    private SanteService santeService;
    @Autowired
    private AutomobileService automobileService;

    @PostMapping("/contrat")
    public String submitContrat(
            @RequestParam("datefin1") String datefin1,
            @RequestParam("IDDevis") Long IDDevis,
            @RequestParam("formFile") MultipartFile file,
            Model model,HttpSession session,
            Contrat contrat) throws Exception {

        System.out.println("datefin" + datefin1);
        System.out.println("IDDevis" + IDDevis);

        User user = (User) session.getAttribute("user");

        // Process the uploaded file
        if (!file.isEmpty()) {
            String uploadDir = "uploads/";
            Files.createDirectories(Paths.get(uploadDir));

            File uploadFile = new File(uploadDir + file.getOriginalFilename());
            file.transferTo(uploadFile); // Save the file

            // Set file details in the Contrat entity
            contrat.setFilePath(uploadFile.getAbsolutePath());
            contrat.setFileName(file.getOriginalFilename());
            contrat.setFileType(file.getContentType());
        }

        // Calculate the LocalDateTime based on the duration
        LocalDateTime endDate;
        switch (datefin1) {
            case "3":
                endDate = LocalDateTime.now().plusMonths(3);
                break;
            case "6":
                endDate = LocalDateTime.now().plusMonths(6);
                break;
            case "12":
                endDate = LocalDateTime.now().plusYears(1);
                break;
            default:
                throw new IllegalArgumentException("Invalid duration selected: " + datefin1);
        }

        // Set the calculated end date in the Contrat object
        contrat.setDatefin(endDate);

        // Retrieve the Devis entity and set it in the Contrat
        Devis devis = devisService.getDevisById(IDDevis);
        contrat.setDevis(devis);
        contrat.setDatedebut(LocalDateTime.now());
        contrat.setUser(user);

        // Save the contract to the database
        contratService.saveContrat(contrat);

        // Add the contract to the model for display
        model.addAttribute("contrat", contrat);

        // Redirect to the result page
        return "redirect:/allContrat";
    }

    @GetMapping("allContrat")
    public String allContrat(Model model ,HttpSession session) {
        List<Contrat> contrats = contratService.getContratByUserAuth(session);
        model.addAttribute("contrats", contrats);
        return "MyContrat";
    }

    @GetMapping("/deleteContract/{id}")
    public ModelAndView deleteContract(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            contratService.deleteContrat(id);
            redirectAttributes.addFlashAttribute("message", "Contract terminate their contract successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting contract. Please try again.");
        }
        return new ModelAndView("redirect:/allContrat");
    }

    @GetMapping("/editContractSante/{id}")
    public String showEditFormSante(@PathVariable("id") Long id, Model model) {
        Contrat contrat = contratService.getContratById(id); // Fetch the contract to edit
        model.addAttribute("contrat", contrat);
        return "EditSanteContract"; // Return the view name for the edit form
    }

    @GetMapping("/editContractAutomobile/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Contrat contrat = contratService.getContratById(id); // Fetch the contract to edit
        model.addAttribute("contrat", contrat);
        return "EditAutomobileContract"; // Return the view name for the edit form
    }

    @PostMapping("/updateContract")
    public String updateContract(Sante sante, RedirectAttributes redirectAttributes) {
        try {
            double montant = sante.calculerMontant();
            System.out.println("montanttyy" + montant);
            Devis  devis =  devisService.getDevisByIdSante(sante.getId());
            devis.setMontant(montant);
            devisService.saveDevis(devis);
            santeService.saveSante(sante); // Update the contract using the service
            redirectAttributes.addFlashAttribute("message", "updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating contract. Please try again.");
        }
        return "redirect:/allContrat"; // Redirect to the contracts page after update
    }

    @PostMapping("/updateContractAutomobile")
    public String updateContractAutomobile(Automobile automobile, RedirectAttributes redirectAttributes) {
        try {
            double montant = automobile.calculerMontant();
            System.out.println("montanttyy" + montant);
            Devis  devis =  devisService.getDevisById(automobile.getId());
            devis.setMontant(montant);
            devisService.saveDevis(devis);
            automobileService.saveAutomobile(automobile); // Update the contract using the service
            redirectAttributes.addFlashAttribute("message", "updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating contract. Please try again.");
        }
        return "redirect:/allContrat"; // Redirect to the contracts page after update
    }


}
