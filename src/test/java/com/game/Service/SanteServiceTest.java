package com.game.Service;

import com.game.entity.Sante;
import com.game.repository.SanteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SanteServiceTest {

    @Mock
    private SanteRepository santeRepository;

    @InjectMocks
    private SanteService santeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveSante_ShouldCallSaveMethod() throws Exception {
        // Arrange
        Sante sante = new Sante();

        // Act
        santeService.saveSante(sante);

        // Assert
        verify(santeRepository, times(1)).save(sante);
    }

    @Test
    void getAllSante_ShouldReturnListOfSante() {
        // Arrange
        List<Sante> santeList = new ArrayList<>();
        when(santeRepository.findAll()).thenReturn(santeList);

        // Act
        List<Sante> result = santeService.getAllSante();

        // Assert
        assertEquals(santeList, result);
        verify(santeRepository, times(1)).findAll();
    }


}
