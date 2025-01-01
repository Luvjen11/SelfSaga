package jennifer.SelfSaga.Chapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterService {
    
    @Autowired
    private ChapterRepository chapterRepository;

    public List<Chapter> getAllChapters() {
        return chapterRepository.findAll();
    }

    public Chapter getChapterById(Long id) {
        return chapterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chapter not found with ID: " + id));
    }

    public Chapter getChapterByTitle(String title) {
        return chapterRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Chapter title not found: " + title));
    }
}
