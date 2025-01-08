package jennifer.SelfSaga.Quest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/selfsaga/users/chapter")
public class QuestController {
    
    @Autowired
    public QuestService questService;

    @GetMapping("/quest/{title}")
    public Optional<Quest> getQuestByTitle(@PathVariable String title) {
        return questService.getQuestByTitle(title);
    }

}
