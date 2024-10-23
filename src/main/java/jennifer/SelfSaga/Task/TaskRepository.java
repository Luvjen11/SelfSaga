package jennifer.SelfSaga.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {
     
    List<Task> findByGoalId(Long goalId);
} 