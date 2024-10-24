package com.game.Service;

import com.game.entity.User;
import com.game.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_ShouldCallSaveMethod() throws Exception {
        // Arrange
        User user = new User();

        // Act
        userService.registerUser(user);

        // Assert
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void authenticateUser_ShouldReturnUser_WhenCredentialsAreValid() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";
        User expectedUser = new User();
        when(userRepository.findByEmailAndPassword(email, password)).thenReturn(expectedUser);

        // Act
        User result = userService.authenticateUser(email, password);

        // Assert
        assertEquals(expectedUser, result);
        verify(userRepository, times(1)).findByEmailAndPassword(email, password);
    }

    @Test
    void authenticateUser_ShouldReturnNull_WhenCredentialsAreInvalid() {
        // Arrange
        String email = "test@example.com";
        String password = "wrongpassword";
        when(userRepository.findByEmailAndPassword(email, password)).thenReturn(null);

        // Act
        User result = userService.authenticateUser(email, password);

        // Assert
        assertNull(result);
        verify(userRepository, times(1)).findByEmailAndPassword(email, password);
    }


}
