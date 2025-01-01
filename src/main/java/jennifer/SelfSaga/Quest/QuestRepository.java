package jennifer.SelfSaga.Quest;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest, Long>{
    Optional<Quest> findByTitle(String title);
}
