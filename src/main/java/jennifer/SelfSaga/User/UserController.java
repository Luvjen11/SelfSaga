package jennifer.SelfSaga.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/selfsaga")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            // handle the registration and return user and successful status
            User registeredUser = userService.registerUser(user);
            return new ResponseEntity<User>(registeredUser, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // for double username and/or email
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/home")
    public String handleWelcome() {
        return "home page";  // Return home page content
    }
}
