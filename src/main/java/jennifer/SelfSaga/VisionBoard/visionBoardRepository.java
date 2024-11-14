package jennifer.SelfSaga.VisionBoard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface visionBoardRepository extends JpaRepository<visionBoard, Long> {

    
}