package jennifer.SelfSaga.Goal;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import jennifer.SelfSaga.User.User;


public interface GoalRepository extends JpaRepository<Goal, Long> {

    List<Goal> findByUser(User user);  // Get goals by user obj 

}
