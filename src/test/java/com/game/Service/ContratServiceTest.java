package com.game.Service;

import com.game.entity.Contrat;
import com.game.entity.User;
import com.game.repository.ContratRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContratServiceTest {

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private HttpSession session;

    @InjectMocks
    private ContratService contratService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveContrat_ShouldCallSaveMethod() {
        // Arrange
        Contrat contrat = new Contrat();

        // Act
        contratService.saveContrat(contrat);

        // Assert
        verify(contratRepository, times(1)).save(contrat);
    }

    @Test
    void getContratByUserAuth_ShouldReturnContractsForAuthenticatedUser() {
        // Arrange
        User user = new User();
        Contrat contrat1 = new Contrat();
        Contrat contrat2 = new Contrat();
        List<Contrat> expectedContracts = Arrays.asList(contrat1, contrat2);

        when(session.getAttribute("user")).thenReturn(user);
        when(contratRepository.findByUser(user)).thenReturn(expectedContracts);

        // Act
        List<Contrat> result = contratService.getContratByUserAuth(session);

        // Assert
        assertEquals(expectedContracts, result);
        verify(contratRepository, times(1)).findByUser(user);
    }

    @Test
    void getContratByUserAuth_ShouldReturnNullWhenUserIsNotAuthenticated() {
        // Arrange
        when(session.getAttribute("user")).thenReturn(null);

        // Act
        List<Contrat> result = contratService.getContratByUserAuth(session);

        // Assert
        assertNull(result);
        verify(contratRepository, never()).findByUser(any());
    }

    @Test
    void deleteContrat_ShouldCallDeleteByIdMethod() {
        // Arrange
        Long contratId = 1L;

        // Act
        contratService.deleteContrat(contratId);

        // Assert
        verify(contratRepository, times(1)).deleteById(contratId);
    }

    @Test
    void getContratById_ShouldReturnContractWhenExists() {
        // Arrange
        Long contratId = 1L;
        Contrat contrat = new Contrat();
        when(contratRepository.findById(contratId)).thenReturn(Optional.of(contrat));

        // Act
        Contrat result = contratService.getContratById(contratId);

        // Assert
        assertEquals(contrat, result);
        verify(contratRepository, times(1)).findById(contratId);
    }

    @Test
    void getContratById_ShouldReturnNullWhenNotExists() {
        // Arrange
        Long contratId = 1L;
        when(contratRepository.findById(contratId)).thenReturn(Optional.empty());

        // Act
        Contrat result = contratService.getContratById(contratId);

        // Assert
        assertNull(result);
        verify(contratRepository, times(1)).findById(contratId);
    }

    @Test
    void updateContrat_ShouldCallSaveMethod() {
        // Arrange
        Contrat contrat = new Contrat();

        // Act
        contratService.updateContrat(contrat);

        // Assert
        verify(contratRepository, times(1)).save(contrat);
    }
}
