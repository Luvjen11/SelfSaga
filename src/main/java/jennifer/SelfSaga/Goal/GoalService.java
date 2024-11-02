package jennifer.SelfSaga.Goal;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(GoalService.class);

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
        
        // making sure goalType is not null
        if (goal.getGoalType() == null) {
            throw new IllegalArgumentException("GoalType cannot be null");
        }

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

        // Validate that updated goal has a non-null goalType
        if (updatedGoal.getGoalType() == null) {
            throw new IllegalArgumentException("GoalType cannot be null");
        }

        //details that can be updated
        existingGoal.setTitle(updatedGoal.getTitle());
        existingGoal.setDescription(updatedGoal.getDescription());
        existingGoal.setDueDate(updatedGoal.getDueDate());
        existingGoal.setStartDate(updatedGoal.getStartDate());
        existingGoal.setGoalType(updatedGoal.getGoalType());

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

    public Goal completeGoal(Long goalId, String username) throws AccessDeniedException {

        //check goal existance
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new NoSuchElementException("Goal not found with ID: " + goalId));
    
        // Check if the user owns the goal
        User user = goal.getUser();
        if (user == null || !user.getUsername().equals(username)) {
            throw new AccessDeniedException("You do not have permission to complete this goal!");
        }

        // check completed goaal
        if (!goal.getIsCompleted()) {
            goal.setIsCompleted(true);
    
            // Award points based on goal type
            int pointsEarned = goal.getGoalType().getPoints();
            user.setXp(user.getXp() + pointsEarned);
            logger.info("Goal completed. Points earned: {}", pointsEarned);
    
            // Check if user should level up
            checkLevelUp(user);
    
            // Save updates
            goalRepository.save(goal);
            userRepository.save(user);
        }

        return goal;
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
