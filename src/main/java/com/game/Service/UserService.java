package com.game.Service;


import com.game.entity.User;
import com.game.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) throws Exception {
        // Logique pour enregistrer l'utilisateur
        userRepository.save(user);
    }

    public User authenticateUser(String email, String password) {
        // Logique d'authentification
        return userRepository.findByEmailAndPassword(email, password);
    }
}
