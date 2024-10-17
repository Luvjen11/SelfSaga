package jennifer.SelfSaga.User;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service

public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //for password hashing

    public User registerUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("This Email is already registered!");
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("This Username is already taken");
        }

        // implement password service logic

        if (!isPasswordStrong(user.getPassword())) {
            throw new RuntimeException("Password is not strong enough!");
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        return userRepository.save(user);  
    }

    private boolean isPasswordStrong(String password) {
        // Check length (at least 8 characters)
        if (password == null || password.length() < 8) {
            return false;
        }

        return true;
    }
}
