package com.game.Controller;

import com.game.Service.DevisService;
import com.game.entity.Contrat;
import com.game.Service.ContratService;
import com.game.entity.Devis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Controller
public class ContratController {

    @Autowired
    private ContratService contratService;
    @Autowired
    private DevisService devisService;

    @PostMapping("/contrat")
    public String submitContrat(
            @RequestParam("datefin1") String datefin1,
            @RequestParam("IDDevis") Long IDDevis,
            @RequestParam("formFile") MultipartFile file,
            Model model,
            Contrat contrat) throws Exception {

        System.out.println("datefin" + datefin1);
        System.out.println("IDDevis" + IDDevis);
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

        // Save the contract to the database
        contratService.saveContrat(contrat);

        // Add the contract to the model for display
        model.addAttribute("contrat", contrat);

        // Redirect to the result page
        return "contratResult"; // Replace with the appropriate view name
    }

}
