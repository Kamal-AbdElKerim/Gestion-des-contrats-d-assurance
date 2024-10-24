package com.game.Service;

import com.game.entity.Habitation;
import com.game.repository.HabitationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HabitationServiceTest {

    @Mock
    private HabitationRepository habitationRepository;

    @InjectMocks
    private HabitationService habitationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveHabitation_ShouldCallSaveMethod() throws Exception {
        // Arrange
        Habitation habitation = new Habitation();

        // Act
        habitationService.saveHabitation(habitation);

        // Assert
        verify(habitationRepository, times(1)).save(habitation);
    }

    @Test
    void getAllHabitation_ShouldReturnListOfHabitations() {
        // Arrange
        List<Habitation> habitations = new ArrayList<>();
        when(habitationRepository.findAll()).thenReturn(habitations);

        // Act
        List<Habitation> result = habitationService.getAllHabitation();

        // Assert
        assertEquals(habitations, result);
        verify(habitationRepository, times(1)).findAll();
    }


}
