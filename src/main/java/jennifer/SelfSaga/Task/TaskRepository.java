package jennifer.SelfSaga.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TaskRepository extends JpaRepository<Task, Long> {
     
    List<Task> findByGoalId(Long goalId);

    //find task by username
    @Query("SELECT t FROM Task t WHERE t.user.username = :username OR t.goal.user.username IS NULL")
    List<Task> findAllByUsername(@Param("username") String username);
} 