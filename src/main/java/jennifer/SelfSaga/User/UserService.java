package jennifer.SelfSaga.User;

import org.springframework.stereotype.Service;

import jennifer.SelfSaga.User.Exceptions.EmailAlreadyRegisteredException;
import jennifer.SelfSaga.User.Exceptions.UsernameAlreadyTakenException;
import jennifer.SelfSaga.User.Exceptions.WeakPasswordException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // for password hashing

    public User registerUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyRegisteredException("This Email is already registered!");
        }
        user.setEmail(user.getEmail());

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyTakenException("This Username is already taken");
        }
        user.setUsername(user.getUsername());

        // implement password service logic

        if (!isPasswordStrong(user.getPassword())) {
            throw new WeakPasswordException("Password is not strong enough! Must be at least 8 characters.");
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

    // login/authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            var userObj = user.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .authorities("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
