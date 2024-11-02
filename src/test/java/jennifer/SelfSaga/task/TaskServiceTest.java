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

import jennifer.SelfSaga.Goal.Goal;
import jennifer.SelfSaga.Goal.GoalRepository;
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

    @Mock
    private GoalRepository goalRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;
    private User user;
    private Goal goal;

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

        goal = new Goal();
        goal.getId();
        task.setGoal(goal);
    }

    @Test
    public void testCompleteTask_SuccessfulCompletion() throws AccessDeniedException {
        // Mocking the repository behavior
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(goalRepository.findById(1L)).thenReturn(Optional.of(goal));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Act

        Task result = taskService.completeTask(1L,1L, "jennifer123");


        // Assert

        assertTrue(result.getIsCompleted());
        assertEquals(10, user.getXp(), "User XP should increase by 10");
    }

    @Test
    public void testCompleteTask_AccessDenied() {
        task.getUser().setUsername("differentUser"); // Simulate another user owning the task

        // Mock the behavior of the repository
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(goalRepository.findById(1L)).thenReturn(Optional.of(goal));

        // Assert that AccessDeniedException is thrown
        assertThrows(AccessDeniedException.class, () -> taskService.completeTask(1L,1L, "jennifer123"));
    }


    @Test
    public void testCompleteTask_TaskTypeMissing() {

        task.setTaskType(null); // Set TaskType to null
        task.getUser().setUsername("jennifer123"); // Ensure usernames match for access



        // Mocking the repository behavior
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(goalRepository.findById(1L)).thenReturn(Optional.of(goal));

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> taskService.completeTask(1L, 1L, "jennifer123"));
    }


    //test level up
    @Test
    public void testCompleteTask_LevelUp() throws AccessDeniedException {
        task.getUser().setXp(240); // Set close to level-up threshold
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(goalRepository.findById(1L)).thenReturn(Optional.of(goal));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Act
        Task result = taskService.completeTask(1L, 1L, "jennifer123");

        // Assert
        assertTrue(result.getIsCompleted());
        assertEquals(250, user.getXp(), "User XP should increase correctly");
        assertEquals(2, user.getLevel(), "User should level up to level 2");
    }

}
