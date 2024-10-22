package jennifer.SelfSaga.Goal;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    List<Goal> findByUsername(String username);  // Get goals for a specific user by username

}
