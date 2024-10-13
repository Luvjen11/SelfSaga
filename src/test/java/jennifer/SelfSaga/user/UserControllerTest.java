package jennifer.SelfSaga.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class UserControllerTest {

    @Test
    @DisplayName("POST /selfsaga/users/register should create a user with valid email, unique username, and strong password")
    void testCreateUser() {
       User user = createNewUser();

       assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}
