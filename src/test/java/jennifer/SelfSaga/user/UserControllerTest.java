package jennifer.SelfSaga.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import jennifer.SelfSaga.User.UserService;


@SpringBootTest
@AutoConfigureMockMvc

public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("POST /selfsaga/users/register - create a user with valid email, unique username, and strong password")
    public void testRegisterUser() throws Exception {

        String registeredUser = "{\"username\": \"testuser\", \"email\": \"test@example.com\", \"password\": \"StrongPass123\"}";

        mockMvc.perform(post("/selfsaga/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(registeredUser))
                .andExpect(status().isCreated())  // Expecting HTTP 201 Created
                .andExpect(jsonPath("$.username").value("testuser"))  // Ensure username matches
                .andExpect(jsonPath("$.email").value("test@example.com"));  // Ensure email matches
    }

}
