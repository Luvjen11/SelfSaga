package jennifer.SelfSaga.Quest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class QuestService {

    @Autowired
    private QuestRepository questRepository;

    public Optional<Quest> getQuestByTitle(String title) {
        return questRepository.findByTitle(title);
    }
}
