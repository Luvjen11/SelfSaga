package jennifer.SelfSaga.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import jennifer.SelfSaga.User.User;
import jennifer.SelfSaga.User.UserService;

@SpringBootTest
@AutoConfigureMockMvc

public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    // Sample user for testing
    private User createSampleUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setEmail("testuser@example.com");
        user.setPassword("password123");
        return user;
    }

    @Test
    @DisplayName("POST /selfsaga/users/register - create a user with valid email, unique username, and strong password")
    public void testRegisterUser_Success() throws Exception {
        // Arrange
        User sampleUser = createSampleUser();
        when(userService.registerUser(sampleUser)).thenReturn(sampleUser);

        // Act & Assert
        mockMvc.perform(post("/selfsaga/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleUser)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("POST /selfsaga/users/register - failure due to duplicate username or email")
    public void testRegisterUser_Failure_DuplicateUser() throws Exception {
        // Arrange
        User sampleUser = createSampleUser();

        // Simulate the behavior of the service when trying to register a duplicate user
        doThrow(new IllegalArgumentException("This Email is already registered!"))
                .when(userService).registerUser(any(User.class));

        // Act & Assert
        mockMvc.perform(post("/selfsaga/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleUser)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("This Email is already registered!"));
    }
}