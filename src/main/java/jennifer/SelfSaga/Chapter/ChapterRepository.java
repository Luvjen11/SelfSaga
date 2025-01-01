package jennifer.SelfSaga.Chapter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    // Add custom query methods maybe findByQuestId, findByCompleted?? 
}
