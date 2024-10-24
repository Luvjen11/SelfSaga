package jennifer.SelfSaga.Task;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jennifer.SelfSaga.Goal.Goal;
import jennifer.SelfSaga.Goal.GoalRepository;
import jennifer.SelfSaga.User.User;
import jennifer.SelfSaga.User.UserRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired 
    private UserRepository userRepository;

    // get task for specifi goal
    public List<Task> getTasksByGoal(Long goalId) {
        return taskRepository.findByGoalId(goalId);
    }

    // get tasks of a user
    public List<Task> getAllTasksByUsername(String username) {
        return taskRepository.findAllByUsername(username);
    }

    // create task for specific goal
    public Task createTask(Task task, Long goalId) {
        if (goalId != null) {
            Goal goal = goalRepository.findById(goalId)
                    .orElseThrow(() -> new IllegalArgumentException("Goal not found with id: " + goalId));
            task.setGoal(goal);
        } else {
            task.setGoal(null);
        }
        return taskRepository.save(task);
    }

    // update task of a specific goal
    public Task updateTask(Long goalId, Long taskId, Task updatedTask) throws NoSuchElementException {

        // find task and makes sure it exsists
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task with ID " + taskId + "not found"));

        // check if task belongs to goal
        if (goalId != null) {
            Goal goal = goalRepository.findById(goalId)
                    .orElseThrow(() -> new NoSuchElementException("Goal not found with id: " + goalId));

            if (!existingTask.getGoal().equals(goal)) {
                throw new IllegalArgumentException("This task does not belong to the goal: " + goalId);
            }
        }

        // update
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setStartDate(updatedTask.getStartDate());
        existingTask.setStatus(updatedTask.getStatus());

        return taskRepository.save(existingTask);
    }

    // delete task of specific goal
    public void deleteTaskByGoal(Long goalId, Long taskId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task with ID " + taskId + " not found"));

        if (goalId != null) {
            Goal goal = goalRepository.findById(goalId)
                    .orElseThrow(() -> new NoSuchElementException("Goal not found with id: " + goalId));

            if (!task.getGoal().equals(goal)) {
                throw new IllegalArgumentException("This task does not belong to the goal: " + goalId);
            }
        }

        taskRepository.deleteById(taskId);
    }

    // delete single task
    public void deleteSingleTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    // for when a task is completed
    public Task completeTask(Long taskId, String username) throws AccessDeniedException {

        //find task
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Task not found"));

        // check if task is completed
        if (!task.getIsCompleted()) {
            task.setIsCompleted(true);

            // then find the user nd deny access if task is not theirs
            User user = task.getUser();
            if (user == null || !user.getUsername().equals(username)) {
                throw new AccessDeniedException("You do not have permission to complete this task");
            }

            // add Xp for task completion
            int xpEarned = task.getTaskType().getXpValue();
            user.setXp(user.getXp() + xpEarned);

            //check if user has to level up
            checkLevelUp(user);

            taskRepository.save(task);
            userRepository.save(user);

        }

        return task;

    }

    public void checkLevelUp(User user) {

        // Collect the current XP and level of the user
        int currentXp = user.getXp();
        int currenLevel = user.getlevel();
        
        //define required xp for next level


        //check if user can level up based on accumulated XP
        // if yes then increment level by one

        // save user updated details

        //method to get xp for next level (use switch)

    }

}
