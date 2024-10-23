package com.game.Service;

import com.game.entity.Devis;
import com.game.entity.DevisStatus;
import com.game.repository.DevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DevisService {

    @Autowired
    private DevisRepository devisRepository;

    // Method to create or save a Devis
    public Devis saveDevis(Devis devis) {
        return devisRepository.save(devis);
    }

    // Method to find a Devis by its ID
    public Devis getDevisById(Long id) throws Exception {
        Optional<Devis> optionalDevis = devisRepository.findById(id);
        if (optionalDevis.isPresent()) {
            return optionalDevis.get();
        } else {
            throw new Exception("Devis not found");
        }
    }

    public void acceptDevis(Long id) throws Exception {
        // Find the Devis by ID
        Devis devis = devisRepository.findById(id)
                .orElseThrow(() -> new Exception("Devis not found with id: " + id));

        // Update the status
        devis.setStatus(DevisStatus.ACCEPTED);

        // Save the updated Devis back to the repository
        devisRepository.save(devis);
    }

    // Method to delete a Devis by its ID
    public void deleteDevis(Long id) throws Exception {
        Devis existingDevis = getDevisById(id);
        devisRepository.delete(existingDevis);
    }
}
