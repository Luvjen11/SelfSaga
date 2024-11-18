package jennifer.SelfSaga.User;

import org.springframework.stereotype.Service;

import jennifer.SelfSaga.User.Exceptions.EmailAlreadyRegisteredException;
import jennifer.SelfSaga.User.Exceptions.UsernameAlreadyTakenException;
import jennifer.SelfSaga.User.Exceptions.WeakPasswordException;
import jennifer.SelfSaga.User.UserProfileDTO.UserProfileDTO;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // for password hashing

    private static final Logger logger = LoggerFactory.getLogger(UserService.class); 

    public User registerUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            logger.info("This Email is already registered");
            throw new EmailAlreadyRegisteredException("This Email is already registered!");
        }
        user.setEmail(user.getEmail());

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            logger.info("This Username is already taken");

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

    //to fetch user profile data
    public UserProfileDTO getUserDetails(String username) {

        //ensure username exsits
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new NoSuchElementException("User not found with username: " + username));

        UserProfileDTO profile = new UserProfileDTO();
        profile.setEmail(user.getEmail());
        profile.setUsername(user.getUsername());
        profile.setXp(user.getXp());
        profile.setLevel(user.getLevel());
        return profile;
    }
}
