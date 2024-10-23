package com.game.Service;

import com.game.entity.Automobile;
import com.game.repository.AutomobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class AutomobileService {

    @Autowired
    private AutomobileRepository automobileRepository;

    public void saveAutomobile(Automobile automobile) throws Exception {
        // Logic to save automobile
        automobileRepository.save(automobile);
    }

    // Additional logic for automobile insurance if needed
}
