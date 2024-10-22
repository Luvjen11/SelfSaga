package jennifer.SelfSaga.Goal;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Goal createGoals(@RequestBody Goal goal, String username) {
        return goalService.createGoal(goal, username);
    }

    // get all goal from a specific user
    @GetMapping("/{username}/goals")
    public List<Goal> getGoalsByUsername(@RequestParam String username) {
        return goalService.getGoalsByUsername(username);
    }
}
