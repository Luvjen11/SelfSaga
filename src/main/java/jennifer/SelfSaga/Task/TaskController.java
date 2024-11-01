package jennifer.SelfSaga.Task;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/selfsaga/users/{username}")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // get tasks by goal
    @GetMapping("/goals/{goalId}/tasks")
    public List<Task> getTasksByGoal(@PathVariable String username, @PathVariable Long goalId) {
        return taskService.getTasksByGoal(goalId);
    }

    // get tasks of a user
    @GetMapping("/tasks")
    public List<Task> getTasksByUsername(@PathVariable String username) {
        return taskService.getAllTasksByUsername(username);
    }

    // create task for specific goal
    @PostMapping("/goals/{goalId}/tasks")
    public Task createTask(@PathVariable String username, @PathVariable Long goalId, @RequestBody Task task) {
        return taskService.createTask(username, task, goalId);
    }

    // create a single task
    @PostMapping("/tasks")
    public Task createTask(@PathVariable String username, @RequestBody Task task) {
        return taskService.createTask(username, task, null);
    }

    // update task in a goal
    @PutMapping("/goals/{goalId}/tasks/{taskId}")
    public Task updateTask(@PathVariable Long goalId, @PathVariable Long taskId, @RequestBody Task task) {
        return taskService.updateTask(goalId, taskId, task);
    }

    // update single task
    @PutMapping("/tasks/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        return taskService.updateTask(null, taskId, task);
    }

    // delete a task to specific goal
    @DeleteMapping("/goals/{goalId}/tasks/{taskId}")
    public void deleteTask(@PathVariable Long goalId, @PathVariable Long taskId) {
        taskService.deleteTaskByGoal(goalId, taskId);
    }

    // delete a single task
    @DeleteMapping("/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteSingleTask(taskId);
    }

    //to gain xp for a single task
    @PatchMapping("/tasks/{taskId}/complete")
    public ResponseEntity<?> completeTask(@PathVariable Long taskId, @RequestParam String username) {
        try {
            Task completedTask = taskService.completeTask(null, taskId, username);
            return ResponseEntity.ok(completedTask);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: " + e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task type is missing: " + e.getMessage());
        }
    }

    //to gain xp for a task connected to a goal
    @PatchMapping("/goals/{goalId}/tasks/{taskId}/complete")
    public ResponseEntity<?> completeTask(@PathVariable Long goalId, @PathVariable Long taskId, @RequestParam String username) {
        try {
            Task completedTask = taskService.completeTask(goalId, taskId, username);
            return ResponseEntity.ok(completedTask);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: " + e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task type is missing: " + e.getMessage());
        }
    }
}