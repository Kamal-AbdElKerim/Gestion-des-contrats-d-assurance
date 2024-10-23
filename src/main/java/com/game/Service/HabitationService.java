package com.game.Service;

import com.game.entity.Habitation;
import com.game.repository.HabitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class HabitationService {

    @Autowired
    private HabitationRepository habitationRepository;

    public void saveHabitation(Habitation habitation) throws Exception {
        // Logic to save habitation
        habitationRepository.save(habitation);
    }

    // Additional logic for habitation insurance if needed
}
