package com.game.Service;

import com.game.entity.Automobile;
import com.game.repository.AutomobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class AutomobileService {

    @Autowired
    private AutomobileRepository automobileRepository;

    public void saveAutomobile(Automobile automobile) throws Exception {
        // Logic to save automobile
        automobileRepository.save(automobile);
    }

    public List<Automobile> getAllAutomobile() {
        // Logic to retrieve all health insurance records
        return automobileRepository.findAll(); // Using JpaRepository's built-in method
    }

    // Additional logic for automobile insurance if needed
}
