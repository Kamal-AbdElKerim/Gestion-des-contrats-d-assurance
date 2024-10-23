package com.game.Service;

import com.game.entity.Contrat;
import com.game.entity.User;
import com.game.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ContratService {

    @Autowired
    private ContratRepository contratRepository;

    public void saveContrat(Contrat contrat) {
        contratRepository.save(contrat);
    }

    public List<Contrat> getContratByUserAuth(HttpSession session) {
        // Get the authenticated user
        User user = (User) session.getAttribute("user");
        if (user != null ) {
            return contratRepository.findByUser(user);
        }
        return null; // or throw an exception, or return an empty list
    }

    public void deleteContrat(Long id) {
        contratRepository.deleteById(id);
    }

    public Contrat getContratById(Long id) {
        return contratRepository.findById(id).orElse(null); // Fetch the contract by ID
    }

    public void updateContrat(Contrat contrat) {
        contratRepository.save(contrat); // Save the updated contract
    }

}
