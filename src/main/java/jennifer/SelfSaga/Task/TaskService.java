package jennifer.SelfSaga.Task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jennifer.SelfSaga.Goal.Goal;
import jennifer.SelfSaga.Goal.GoalRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private GoalRepository goalRepository;

    //get task for specifi goal
    public List<Task> getTasksByGoal(Long goalId) {
        return taskRepository.findByGoalId(goalId);
    }

    //create task for specific goal
    public Task createTask(Task task, Long goalId) {
        Goal goal = goalRepository.findById(goalId).orElseThrow(() -> new IllegalArgumentException("Goal not found with id: " + goalId));
        task.setGoal(goal);
        return taskRepository.save(task);
    }

    
}
