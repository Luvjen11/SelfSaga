package jennifer.SelfSaga.Task;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.NoSuchElementException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jennifer.SelfSaga.Goal.Goal;
import jennifer.SelfSaga.Goal.GoalRepository;
import jennifer.SelfSaga.User.User;
import jennifer.SelfSaga.User.UserRepository;

@Service
@Transactional
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class); 

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
    public Task createTask(String username, Task task, Long goalId) {

        logger.info("starting create task part 2");
        // get user first
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("User not found with username: " + username));
        
        //assign task to user
        task.setUser(user);
        
        if (goalId != null) {
            Goal goal = goalRepository.findById(goalId)
                    .orElseThrow(() -> new IllegalArgumentException("Goal not found with id: " + goalId));
            task.setGoal(goal);
        } else {
            task.setGoal(null);  // then creates independent task
        }
        return taskRepository.save(task);
    }

    // update task of a specific goal
    public Task updateTask(Long goalId, Long taskId, Task updatedTask) throws NoSuchElementException {

        // find task and makes sure it exsists
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task with ID " + taskId + " not found"));

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
        existingTask.setIsCompleted(updatedTask.getIsCompleted());

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
    public Task completeTask(Long goalId, Long taskId, String username) throws AccessDeniedException {

        // Find task by ID
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + taskId));
        
        // If a goalId is provided, verify the task belongs to the goal
        if (goalId != null) {
            Goal goal = goalRepository.findById(goalId)
                    .orElseThrow(() -> new NoSuchElementException("Goal not found with ID: " + goalId));
            
            if (!goal.equals(task.getGoal())) {
                throw new IllegalArgumentException("This task does not belong to the specified goal: " + goalId);
            }
        }
        
        // Verify the user owns the task
        User user = task.getUser();
        if (user == null || !user.getUsername().equals(username)) {
            throw new AccessDeniedException("You do not have permission to complete this task");
        }
    
        // Check if task is already completed
        if (!task.getIsCompleted()) {
            task.setIsCompleted(true);
    
            // Add XP for task completion if TaskType is not null
            if (task.getTaskType() != null) {
                int xpEarned = task.getTaskType().getXpValue();
                logger.debug("Task completed. XP earned: {}", xpEarned);
                user.setXp(user.getXp() + xpEarned);
            } else {
                throw new IllegalStateException("TaskType is missing for task " + task.getId());
            }
    
            // Check if the user should level up
            checkLevelUp(user);
    
            // Save the updated task and user
            taskRepository.save(task);
            userRepository.save(user);
        }
    
        return task;
    }
    

    public void checkLevelUp(User user) {

        // Collect the current XP and level of the user
        int currentXp = user.getXp();
        int currentLevel = user.getLevel();
        
        //define required xp for next level
        //check if user can level up based on accumulated XP
        while (currentXp >= xpForNextLevel(currentLevel + 1)) {

            // if yes then increment level by one
            currentLevel++;
            // save user updated details
            user.setLevel(currentLevel); 
        }

        userRepository.save(user);

    }

    //method to get xp for next level (use switch)
    public int xpForNextLevel(int level) {
        switch (level) {
            case 1:
                return 100;
            case 2:
                return 250;
            case 3:
                return 400;
            case 4:
                return 600;
            case 5:
                return 850;
            default:  
                return 1000 + (level - 5) * 200; // increse of 200
        }
    } 

}
