package com.game.Service;

import com.game.entity.Contrat;
import com.game.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContratService {

    @Autowired
    private ContratRepository contratRepository;

    public void saveContrat(Contrat contrat) {
        contratRepository.save(contrat);
    }
}
