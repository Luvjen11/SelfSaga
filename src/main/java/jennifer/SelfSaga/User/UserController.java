package jennifer.SelfSaga.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jennifer.SelfSaga.User.Exceptions.EmailAlreadyRegisteredException;
import jennifer.SelfSaga.User.Exceptions.UsernameAlreadyTakenException;
import jennifer.SelfSaga.User.Exceptions.WeakPasswordException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/selfsaga")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        try {
            // handle the registration and return user and successful status
            User registeredUser = userService.registerUser(user);
            return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
        } catch (EmailAlreadyRegisteredException | UsernameAlreadyTakenException | WeakPasswordException e) {
            // Return the specific error message for bad requests
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            // for double username and/or email
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
