package jennifer.SelfSaga.Task;

import java.util.List;
import java.util.NoSuchElementException;

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

    //get tasks of a user
    public List<Task> getAllTasksByUsername(String username) {
        return taskRepository.findAllByUsername(username);
    }

    //create task for specific goal
    public Task createTask(Task task, Long goalId) {
        Goal goal = goalRepository.findById(goalId).orElseThrow(() -> new IllegalArgumentException("Goal not found with id: " + goalId));
        task.setGoal(goal);
        return taskRepository.save(task);
    }

    //create a task without specific goal
    public Task singleTask(Task task) {
        return taskRepository.save(task);
    }

    //update task of a specific goal
    public Task updateTask(Long goalId, Long taskId, Task updatedTask) throws NoSuchElementException {

        Goal goal = goalRepository.findById(goalId).orElseThrow(() -> new NoSuchElementException("Goal not found with id: " + goalId));
        // find task and makes sure it exsists 
        Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException( "Task with ID " + taskId + "not found"));

        //check if task belongs to goal
        if (!existingTask.getGoal().equals(goal)){
            throw new IllegalArgumentException("This task does not belong to the goal: " + goalId);
        } 

        // update 
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setStartDate(updatedTask.getStartDate());
        existingTask.setStatus(updatedTask.getStatus());

        return taskRepository.save(existingTask);
    }

    //update single task
    public Task updateTask(Long taskId, Task updatedTask) throws NoSuchElementException {

        // find task and makes sure it exsists 
        Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException( "Task with ID " + taskId + "not found"));

        // update 
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setStartDate(updatedTask.getStartDate());
        existingTask.setStatus(updatedTask.getStatus());

        return taskRepository.save(existingTask);
    }

    //delete task of specific goal
    public void deleteTaskByGoal(Long goalId, Long taskId) {
        Goal goal = goalRepository.findById(goalId).orElseThrow(() -> new NoSuchElementException("Goal not found with id: " + goalId));

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Task with ID " + taskId + " not found"));

        if (!task.getGoal().equals(goal)) {
            throw new IllegalArgumentException("This task does not belong to the goal: " + goalId);
        }

        taskRepository.deleteById(taskId);
    }

    //delete single task 
    public void deleteSingleTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
    
}
