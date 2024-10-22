package jennifer.SelfSaga.Goal;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    //get goals by username
    public List<Goal> getGoalsByUsername(String username) {
        return goalRepository.findByUsername(username);
    }

    //create a new goal
    public Goal createGoal(Goal goal, String username) throws IllegalArgumentException {
        goal.setUsername(username);
        return goalRepository.save(goal);
    }

    //update existing goal
    public Goal updateGoal(Long goalId, String username, Goal updatedGoal) throws NoSuchElementException {
        
        // find goal and makes sure it exsists 
        Goal existingGoal = goalRepository.findById(goalId).orElseThrow(() -> new NoSuchElementException( goalId + "not found"));
        
        if (!existingGoal.getUsername().equals(username)){
            throw new IllegalArgumentException("This goal does not belong to the user: " + username);
        }

        //details that can be updated
        existingGoal.setTitle(updatedGoal.getTitle());
        existingGoal.setDescription(updatedGoal.getDescription());
        existingGoal.setDueDate(updatedGoal.getDueDate());
        existingGoal.setStartDate(updatedGoal.getStartDate());

        //updates goal
        return goalRepository.save(existingGoal);
    }

    //delete goal
    public void deleteGoal(Goal goal) {
        goalRepository.delete(goal);
    }

}
