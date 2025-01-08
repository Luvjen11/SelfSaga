package jennifer.SelfSaga.Chapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/selfsaga/users")
public class ChapterController {

    @Autowired
    public ChapterService chapterService;
    
    @GetMapping("/chapter/{title}")
    public Chapter getChapterByTitle(@PathVariable String title) {
        return chapterService.getChapterByTitle(title);
    }
}
