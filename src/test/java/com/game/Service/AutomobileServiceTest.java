package com.game.Service;


import com.game.entity.Automobile;
import com.game.repository.AutomobileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AutomobileServiceTest {

    @Mock
    private AutomobileRepository automobileRepository;

    @InjectMocks
    private AutomobileService automobileService;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveAutomobile_ShouldCallSaveMethod() throws Exception {
        // Arrange
        Automobile automobile = new Automobile();
        // Act
        automobileService.saveAutomobile(automobile);
        // Assert
        verify(automobileRepository, times(1)).save(automobile);
    }

    @Test
    void getAllAutomobile_ShouldReturnListOfAutomobiles() {
        // Arrange
        Automobile auto1 = new Automobile();
        Automobile auto2 = new Automobile();
        List<Automobile> expectedAutomobiles = Arrays.asList(auto1, auto2);
        when(automobileRepository.findAll()).thenReturn(expectedAutomobiles);

        // Act
        List<Automobile> result = automobileService.getAllAutomobile();

        // Assert
        assertEquals(expectedAutomobiles, result);
        verify(automobileRepository, times(1)).findAll();
    }
}

