package jennifer.SelfSaga.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.catalina.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class UserControllerTest {

    @Test
    @DisplayName("POST /selfsaga/users/register - create a user with valid email, unique username, and strong password")
    public void testCreateUser() {

        //create a new user
        User user = new User("testuser", "test@example.com", "StrongPass123");

        assertEquals("testuser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("StrongPass123", user.getPassword());
        assertNotNull(user); // user is not null
    }

    @Test 
    @DisplayName("/selfsaga/users/login - authenticate User")
    public void testLoginUser() {

        //define username and password
        String username = "testuser";
        String password = "StrongPass123";

        boolean loginSuccessful = "testuser1".equals(username) && "StrongPass123".equals(password);

        assertTrue(loginSuccessful, "Login Failed");
    }



}
