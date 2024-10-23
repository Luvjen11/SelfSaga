package jennifer.SelfSaga.Task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/selfsaga/users/{username}")
public class TaskController {

    @Autowired
    private TaskService taskService;

    //get tasks by goal
    @GetMapping("/goals/{goalId}/tasks")
    public List<Task> getTasksByGoal(@PathVariable String username, @PathVariable Long goalId) {
        return taskService.getTasksByGoal(goalId);
    }

    //get tasks of a user
    @GetMapping("/tasks")
    public List<Task> getTasksByUsername(@PathVariable String username) {
        return taskService.getAllTasksByUsername(username);
    }


}
