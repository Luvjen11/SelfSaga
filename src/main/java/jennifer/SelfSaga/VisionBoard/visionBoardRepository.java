package jennifer.SelfSaga.VisionBoard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface VisionBoardRepository extends JpaRepository<VisionBoard, Long> {

    @Query("SELECT v FROM VisionBoard v WHERE v.user.username = :username")
    List<VisionBoard> findAllByUsername(@Param("username") String username);
}
