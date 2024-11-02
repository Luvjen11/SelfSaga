package jennifer.SelfSaga.Goal;

import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/selfsaga/users/{username}/goals")

public class GoalController {

    @Autowired
    private GoalService goalService;

    // create a new goal
    @PostMapping
    public Goal createGoals(@RequestBody Goal goal, @PathVariable String username) {
        return goalService.createGoal(goal, username);
    }

    // get all goal from a specific user
    @GetMapping
    public List<Goal> getGoalsByUsername(@RequestParam String username) {
        return goalService.getGoalsByUsername(username);
    }

    @PutMapping("/{goalId}")
    public Goal updateGoal(@PathVariable Long goalId, @PathVariable String username, @RequestBody Goal goal) {
        return goalService.updateGoal(goalId, username, goal);
    }

    @DeleteMapping("/{goalId}")
    public void deleteGoal(@PathVariable Long goalId, @PathVariable String username) {
        goalService.deleteGoal(goalId, username);
    }

    // to gain xp for completed goal
    @PatchMapping("/{goalId}/complete")
    public ResponseEntity<?> completeGoal(@PathVariable String username, @PathVariable Long goalId) {
        try {
            Goal completedGoal = goalService.completeGoal(goalId, username);
            return ResponseEntity.ok(completedGoal);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: " + e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
