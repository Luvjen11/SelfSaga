package jennifer.SelfSaga.Goal;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/selfsaga/users")

public class GoalController {

    @Autowired
    private GoalService goalService;

    //create a new goal
    @PostMapping("/{username}/goals")
    public Goal createGoals(@RequestBody Goal goal, @PathVariable String username) {
        return goalService.createGoal(goal, username);
    }

    // get all goal from a specific user
    @GetMapping("/{username}/goals")
    public List<Goal> getGoalsByUsername(@RequestParam String username) {
        return goalService.getGoalsByUsername(username);
    }

    @PutMapping("/{username}/goals/{goalId}")
    public Goal updateGoal(@PathVariable Long goalId, @PathVariable String username, @RequestBody Goal goal) {
        return goalService.updateGoal(goalId, username, goal);
    }

    @DeleteMapping("{username}/goals/{goalId}")
    public void deleteGoal(@PathVariable Long goalId, @PathVariable String username) {
        goalService.deleteGoal(goalId, username);
    }
}
