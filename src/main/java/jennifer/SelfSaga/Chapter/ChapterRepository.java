package jennifer.SelfSaga.Chapter;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    // Add custom query methods maybe findByQuestId, findByCompleted?? 
    Optional<Chapter> findByTitle(String title);
}
