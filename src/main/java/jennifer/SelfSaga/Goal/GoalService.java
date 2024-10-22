package jennifer.SelfSaga.Goal;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jennifer.SelfSaga.User.User;
import jennifer.SelfSaga.User.UserRepository;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private UserRepository userRepository;

    //get goals by username
    public List<Goal> getGoalsByUsername(String username) {
        
        //first find user
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("User with username " + username + " not found"));
        
        //return ser goal
        return goalRepository.findByUser(user);
    }

    //create a new goal
    public Goal createGoal(Goal goal, String username) throws IllegalArgumentException {
        
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("User with username " + username + " not found"));
        goal.setUser(user); //link goal to user
        return goalRepository.save(goal);
    }

    //update existing goal
    public Goal updateGoal(Long goalId, String username, Goal updatedGoal) throws NoSuchElementException {
        
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("User not found: " + username));

        // find goal and makes sure it exsists 
        Goal existingGoal = goalRepository.findById(goalId).orElseThrow(() -> new NoSuchElementException( "Goal with ID " + goalId + "not found"));
        
        if (!existingGoal.getUser().equals(user)){
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
    public void deleteGoal(Long goalId, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("User not found: " + username));

        Goal goal = goalRepository.findById(goalId).orElseThrow(() -> new NoSuchElementException("Goal not found: " + goalId));
        if (!goal.getUser().equals(user)) {
            throw new IllegalArgumentException("This goal does not belong to the user: " + username);
        }

        goalRepository.delete(goal);

    }

}
