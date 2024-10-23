package com.game.Service;

import com.game.entity.Sante;
import com.game.repository.SanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class SanteService {

    @Autowired
    private SanteRepository santeRepository;

    public void saveSante(Sante sante) throws Exception {
        // Logic to save health insurance data
        santeRepository.save(sante);
    }

    public List<Sante> getAllSante() {
        // Logic to retrieve all health insurance records
        return santeRepository.findAll(); // Using JpaRepository's built-in method
    }





}
