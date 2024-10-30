package jennifer.SelfSaga.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jennifer.SelfSaga.Task.Task;
import jennifer.SelfSaga.Task.TaskRepository;
import jennifer.SelfSaga.Task.TaskService;
import jennifer.SelfSaga.Task.TaskType;
import jennifer.SelfSaga.User.User;
import jennifer.SelfSaga.User.UserRepository;


@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    //mock repo instead of using main database
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;
    private User user;

    @BeforeEach
    public void setUp() {

        user = new User();
        user.setUsername("jennifer123");
        user.setXp(0); // initial XP

        task = new Task();
        task.getId();
        task.setUser(user);
        task.setIsCompleted(false);

        // XP awarded for task completion
        task.setTaskType(TaskType.DAILY);
    }

    @Test
    public void testCompleteTask_SuccessfulCompletion() throws AccessDeniedException {
        // Mocking the repository behavior
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Act

        Task result = taskService.completeTask(1L, "jennifer123");


        // Assert

        assertTrue(result.getIsCompleted());
        assertEquals(10, user.getXp(), "User XP should increase by 10");
    }

    @Test
    public void testCompleteTask_AccessDenied() {
        task.getUser().setUsername("differentUser"); // Simulate another user owning the task

        // Mock the behavior of the repository
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // Assert that AccessDeniedException is thrown
        assertThrows(AccessDeniedException.class, () -> taskService.completeTask(1L, "jennifer123"));
    }


    @Test
    public void testCompleteTask_TaskTypeMissing() {

        task.setTaskType(null); // Set TaskType to null
        task.getUser().setUsername("jennifer123"); // Ensure usernames match for access



        // Mocking the repository behavior
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> taskService.completeTask(1L, "jennifer123"));
    }

}
