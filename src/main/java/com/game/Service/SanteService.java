package com.game.Service;

import com.game.entity.Sante;
import com.game.repository.SanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class SanteService {

    @Autowired
    private SanteRepository santeRepository;

    public void saveSante(Sante sante) throws Exception {
        // Logic to save health insurance data
        santeRepository.save(sante);
    }

    // Additional logic for health insurance if needed
}
