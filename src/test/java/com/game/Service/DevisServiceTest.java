package com.game.Service;

import com.game.entity.Devis;
import com.game.entity.DevisStatus;
import com.game.repository.DevisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DevisServiceTest {

    @Mock
    private DevisRepository devisRepository;

    @InjectMocks
    private DevisService devisService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveDevis_ShouldCallSaveMethod() {
        // Arrange
        Devis devis = new Devis();

        // Act
        devisService.saveDevis(devis);

        // Assert
        verify(devisRepository, times(1)).save(devis);
    }

    @Test
    void getDevisById_ShouldReturnDevisWhenExists() throws Exception {
        // Arrange
        Long devisId = 1L;
        Devis devis = new Devis();
        when(devisRepository.findById(devisId)).thenReturn(Optional.of(devis));

        // Act
        Devis result = devisService.getDevisById(devisId);

        // Assert
        assertEquals(devis, result);
        verify(devisRepository, times(1)).findById(devisId);
    }

    @Test
    void getDevisById_ShouldThrowExceptionWhenNotFound() {
        // Arrange
        Long devisId = 1L;
        when(devisRepository.findById(devisId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            devisService.getDevisById(devisId);
        });
        assertEquals("Devis not found", exception.getMessage());
        verify(devisRepository, times(1)).findById(devisId);
    }

    @Test
    void acceptDevis_ShouldUpdateStatus() throws Exception {
        // Arrange
        Long devisId = 1L;
        Devis devis = new Devis();
        when(devisRepository.findById(devisId)).thenReturn(Optional.of(devis));

        // Act
        devisService.acceptDevis(devisId);

        // Assert
        assertEquals(DevisStatus.ACCEPTED, devis.getStatus());
        verify(devisRepository, times(1)).save(devis);
    }

    @Test
    void acceptDevis_ShouldThrowExceptionWhenNotFound() {
        // Arrange
        Long devisId = 1L;
        when(devisRepository.findById(devisId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            devisService.acceptDevis(devisId);
        });
        assertEquals("Devis not found with id: " + devisId, exception.getMessage());
        verify(devisRepository, never()).save(any());
    }

    @Test
    void deleteDevis_ShouldDeleteDevisWhenExists() throws Exception {
        // Arrange
        Long devisId = 1L;
        Devis devis = new Devis();
        when(devisRepository.findById(devisId)).thenReturn(Optional.of(devis));

        // Act
        devisService.deleteDevis(devisId);

        // Assert
        verify(devisRepository, times(1)).delete(devis);
    }

    @Test
    void deleteDevis_ShouldThrowExceptionWhenNotFound() {
        // Arrange
        Long devisId = 1L;
        when(devisRepository.findById(devisId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            devisService.deleteDevis(devisId);
        });
        assertEquals("Devis not found", exception.getMessage());
        verify(devisRepository, never()).delete(any());
    }

    @Test
    void getDevisByIdSante_ShouldReturnDevisWhenExists() throws Exception {
        // Arrange
        Long santeId = 1L;
        Devis devis = new Devis();
        when(devisRepository.findBySanteId(santeId)).thenReturn(devis);

        // Act
        Devis result = devisService.getDevisByIdSante(santeId);

        // Assert
        assertEquals(devis, result);
        verify(devisRepository, times(1)).findBySanteId(santeId);
    }
}
