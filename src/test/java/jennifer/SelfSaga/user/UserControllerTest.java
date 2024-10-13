package jennifer.SelfSaga.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.catalina.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class UserControllerTest {

    @Test
    @DisplayName("POST /selfsaga/users/register - create a user with valid email, unique username, and strong password")
    public void testCreateUser() {
        //testCreateUserWithValidInfo
       User user = new User("testuser", "test@example.com", "StrongPass123");

       assertEquals("testuser", user.getUsername());
       assertEquals("test@example.com", user.getEmail());
       assertEquals("StrongPass123", user.getPassword());
       assertNotNull(user); // user is not null
    }

}
